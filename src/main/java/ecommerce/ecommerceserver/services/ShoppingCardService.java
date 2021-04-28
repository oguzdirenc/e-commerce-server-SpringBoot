package ecommerce.ecommerceserver.services;

import ecommerce.ecommerceserver.domain.Book;
import ecommerce.ecommerceserver.domain.ShoppingCart;

import java.util.List;
import java.util.UUID;

public interface ShoppingCardService {

    ShoppingCart addBookToCard(UUID bookID, String username);

    String removeBookFromCard(UUID bookId,String username);

    Book updateBookInCard(Book book, UUID shoppingCartID);

    void setPrice(ShoppingCart shoppingCart);

    ShoppingCart saveShoppingCart(List<Book> bookList,String username);

    ShoppingCart getShoppingCartByName(String shoppingCartName);

    List<Book> userShoppingCart(String username);

    List<Book> getShoppingCartBookList(UUID shoppingCartId);

    ShoppingCart getShoppingCartById(UUID shoppingCartId);


}
