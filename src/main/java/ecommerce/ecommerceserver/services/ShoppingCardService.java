package ecommerce.ecommerceserver.services;

import ecommerce.ecommerceserver.domain.Book;
import ecommerce.ecommerceserver.domain.ShoppingCart;

import java.util.List;
import java.util.UUID;

public interface ShoppingCardService {

    Book addBookToCard(UUID bookID, ShoppingCart shoppingCart);

    String removeBookFromCard(UUID bookId,ShoppingCart shoppingCart);

    Book updateBookInCard(Book book, UUID shoppingCartID);

    void setPrice(ShoppingCart shoppingCart);

    ShoppingCart saveShoppingCart(ShoppingCart shoppingCart);

    ShoppingCart getShoppingCartByName(String shoppingCartName);

    List<Book> getShoppingCartBookList(UUID shoppingCartId);

    ShoppingCart getShoppingCArtById(UUID shoppingCartId);


}
