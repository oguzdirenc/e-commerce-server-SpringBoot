package ecommerce.ecommerceserver.services;

import ecommerce.ecommerceserver.domain.Book;

import java.util.List;
import java.util.UUID;

public interface BookService {

    Book getBookById(UUID id);

    void saveBookRate(Integer commentRate,UUID bookId);

    String deleteBookById(UUID id);

    Book saveBook(Book book);



    Book getBookByName(String bookName);

    List<Book> getAllBooks();
}
