package ecommerce.ecommerceserver.services;

import ecommerce.ecommerceserver.domain.Book;
import ecommerce.ecommerceserver.domain.ShoppingCart;
import ecommerce.ecommerceserver.response.BookSizeResponse;
import ecommerce.ecommerceserver.response.TotalPriceResponse;

import java.util.List;
import java.util.Map;
import java.util.UUID;

public interface ShoppingCardService {

    //2
    ShoppingCart addBookToCard(UUID bookID, String username);


    void setPrice(ShoppingCart shoppingCart);

    //3
    List<BookSizeResponse> userShoppingCart(String username);


    ShoppingCart getShoppingCartById(UUID shoppingCartId);

    //4
    TotalPriceResponse getTotalPrice(String username);

    //1
    List<Book> decreaseBookOrderFromCard(UUID bookId,String username);

    List<Book> removeBookFromCart(String username,UUID bookId);


}
