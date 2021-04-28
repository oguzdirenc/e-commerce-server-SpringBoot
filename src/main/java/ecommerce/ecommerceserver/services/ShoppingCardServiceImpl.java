package ecommerce.ecommerceserver.services;

import ecommerce.ecommerceserver.domain.ApplicationUser;
import ecommerce.ecommerceserver.domain.Book;
import ecommerce.ecommerceserver.domain.ShoppingCart;
import ecommerce.ecommerceserver.exceptions.NotFoundException;
import ecommerce.ecommerceserver.repositories.BookRepository;
import ecommerce.ecommerceserver.repositories.ShoppingCartRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
@Service
@RequiredArgsConstructor
public class ShoppingCardServiceImpl implements ShoppingCardService {

    private final BookService bookService;
    private final ShoppingCartRepository shoppingCartRepository;
    private final ApplicationUserService applicationUserService;

    //Significant
    @Override
    public ShoppingCart addBookToCard(UUID bookID, String username) {

        Book book = bookService.getBookById(bookID);
        ShoppingCart shoppingCart = applicationUserService.getShoppingCartByUsername(username);
        shoppingCart.getShoppingCartBooks().add(book);
        shoppingCartRepository.save(shoppingCart);
        return shoppingCart;
    }

    //Significant
    @Override
    public String removeBookFromCard(UUID bookId,String username) {

        Book bookToRemove = null;
        ShoppingCart shoppingCart = applicationUserService.getShoppingCartByUsername(username);

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


        for(Book book : shoppingCart.getShoppingCartBooks()){
            shoppingCart.setTotalPriceShoppingCart( shoppingCart.getTotalPriceShoppingCart()
                    .add((book.getBookPrice()).multiply(new BigDecimal(book.getOrderSize()))));

        }

    }

    @Override
    public ShoppingCart saveShoppingCart(List<Book> bookList,String username) {

            ApplicationUser user = applicationUserService.getUserByUsername(username);
            ShoppingCart shoppingCart = user.getShoppingCart();
            shoppingCart.setShoppingCartBooks(bookList);
            shoppingCart.setShoppingCartName(username);
            return shoppingCartRepository.save(shoppingCart);
    }

    @Override
    public ShoppingCart getShoppingCartByName(String shoppingCartName) {
        return shoppingCartRepository.getShoppingCartByName(shoppingCartName)
                .orElseThrow(() -> new NotFoundException("Shopping cart not found"));

    }

    @Override
    public List<Book> userShoppingCart(String username) {

        ApplicationUser user = applicationUserService.getUserByUsername(username);

        return user.getShoppingCart().getShoppingCartBooks();
    }

    @Override
    public List<Book> getShoppingCartBookList(UUID shoppingCartId) {
        List<Book> shoppingCartBooks = new ArrayList<>();

        ShoppingCart shoppingCart = getShoppingCartById(shoppingCartId);

        for(Book book : shoppingCart.getShoppingCartBooks()){
            shoppingCartBooks.add(book);
        }

        return shoppingCartBooks;
    }

    @Override
    public ShoppingCart getShoppingCartById(UUID shoppingCartId) {

        return shoppingCartRepository.findById(shoppingCartId)
                .orElseThrow(()-> new NotFoundException("Shopping cart not found"));
    }

}
