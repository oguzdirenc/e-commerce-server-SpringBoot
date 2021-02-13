package ecommerce.ecommerceserver.services;

import ecommerce.ecommerceserver.domain.Book;
import ecommerce.ecommerceserver.domain.ShoppingCart;
import ecommerce.ecommerceserver.exceptions.NotFoundException;
import ecommerce.ecommerceserver.repositories.ShoppingCartRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.UUID;
@Service
@RequiredArgsConstructor
public class ShoppingCardServiceImpl implements ShoppingCardService {

    private final BookService bookService;
    private final ShoppingCartRepository shoppingCartRepository;

    @Override
    public Book addBookToCard(UUID bookID, ShoppingCart shoppingCart) {

        Book book = bookService.getBookById(bookID);
        shoppingCart.getShoppingCartBooks().add(book);
        setPrice(shoppingCart);
        return book;

    }

    @Override
    public String removeBookFromCard(UUID bookId,ShoppingCart shoppingCart) {

        Book bookToRemove = null;

        for (Book book : shoppingCart.getShoppingCartBooks() ){
            if(book.getBookId() == bookId)   bookToRemove = book;
        }
        if(bookToRemove!=null) {
            shoppingCart.getShoppingCartBooks().remove(bookToRemove);
            setPrice(shoppingCart);
        }else throw new NotFoundException("Book not found");

        return "Book Removed";
    }

    @Override
    public Book updateBookInCard(Book book, UUID shoppingCartId) {

        ShoppingCart shoppingCart = shoppingCartRepository.findById(shoppingCartId)
                .orElseThrow(() ->new NotFoundException("Shopping Cart not found"));


        for(Book bookToUpdate : shoppingCart.getShoppingCartBooks()){
            if(bookToUpdate.getBookId() == book.getBookId()){
                bookToUpdate.setOrderSize(book.getOrderSize());
                setPrice(shoppingCart);
                return bookToUpdate;
            }
        }
        return book;
    }

    @Override
    public void setPrice(ShoppingCart shoppingCart) {

        BigDecimal price = new BigDecimal(0);

        for(Book book : shoppingCart.getShoppingCartBooks()){
            price = price.add((book.getBookPrice()).multiply(new BigDecimal(book.getOrderSize())));

        }

        shoppingCart.setTotalPriceShoppingCart(price);
    }

}
