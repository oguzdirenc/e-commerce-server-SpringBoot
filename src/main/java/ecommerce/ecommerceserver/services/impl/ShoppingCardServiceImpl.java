package ecommerce.ecommerceserver.services.impl;

import ecommerce.ecommerceserver.domain.ApplicationUser;
import ecommerce.ecommerceserver.domain.Book;
import ecommerce.ecommerceserver.domain.Cargo;
import ecommerce.ecommerceserver.domain.ShoppingCart;
import ecommerce.ecommerceserver.exceptions.NotFoundException;
import ecommerce.ecommerceserver.repositories.ShoppingCartRepository;
import ecommerce.ecommerceserver.response.BookSizeResponse;
import ecommerce.ecommerceserver.response.TotalPriceResponse;
import ecommerce.ecommerceserver.services.ApplicationUserService;
import ecommerce.ecommerceserver.services.BookService;
import ecommerce.ecommerceserver.services.ShoppingCardService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.groupingBy;

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



    @Override
    public void setPrice(ShoppingCart shoppingCart) {


        for(Book book : shoppingCart.getShoppingCartBooks()){
            shoppingCart.setTotalPriceShoppingCart( shoppingCart.getTotalPriceShoppingCart()
                    .add((book.getBookPrice()).multiply(new BigDecimal(book.getOrderSize()))));

        }

    }


    @Override
    public List<BookSizeResponse> userShoppingCart(String username) {

        ApplicationUser user = applicationUserService.getUserByUsername(username);

        var shoppingCartBooks = user.getShoppingCart().getShoppingCartBooks();

        var bookListMap = shoppingCartBooks.stream()
                .collect(groupingBy(Book :: getBookId));


        var bookSizeResponseList = new ArrayList<BookSizeResponse>();

        bookListMap.keySet()
                .forEach(key -> {
                    var response = new BookSizeResponse();
                    response.setBook(bookService.getBookById(key));
                    response.setCount(bookListMap.get(key).size());
                    bookSizeResponseList.add(response);
                });

        return bookSizeResponseList;
    }



    @Override
    public ShoppingCart getShoppingCartById(UUID shoppingCartId) {

        return shoppingCartRepository.findById(shoppingCartId)
                .orElseThrow(()-> new NotFoundException("Shopping cart not found"));
    }

    @Override
    public TotalPriceResponse getTotalPrice(String username) {

        ApplicationUser user = applicationUserService.getUserByUsername(username);

        var shoppingCartBooks = user.getShoppingCart().getShoppingCartBooks();

        BigDecimal price = BigDecimal.ZERO;
        BigDecimal totalPrice = BigDecimal.ZERO;
        var cargo = new Cargo();

        for(Book book : shoppingCartBooks){
            price = price.add(book.getBookPrice());
        }

        totalPrice = price.add(cargo.getCargoPrice());

        var response = new TotalPriceResponse();
        response.setPrice(price);
        response.setTotalPrice(totalPrice);
        response.setCargo(cargo);

        return response;
    }

    @Override
    public List<Book> decreaseBookOrderFromCard(UUID bookId, String username) {

        ApplicationUser user = applicationUserService.getUserByUsername(username);

        Book book = bookService.getBookById(bookId);
        var shoppingCart = user.getShoppingCart();
        shoppingCart.getShoppingCartBooks().remove(book);
        shoppingCartRepository.save(shoppingCart);

        return user.getShoppingCart().getShoppingCartBooks();
    }

    @Override
    public List<Book> removeBookFromCart(String username, UUID bookId) {

        var user = applicationUserService.getUserByUsername(username);
        var bookOrders = user.getShoppingCart().getShoppingCartBooks();
        var bookList = bookOrders.stream()
                .filter(book -> !book.getBookId().equals(bookId) )
                .collect(Collectors.toList());

        ShoppingCart shoppingCart = user.getShoppingCart();
        shoppingCart.setShoppingCartBooks(bookList);
        shoppingCartRepository.save(shoppingCart);

        return user.getShoppingCart().getShoppingCartBooks();
    }

}
