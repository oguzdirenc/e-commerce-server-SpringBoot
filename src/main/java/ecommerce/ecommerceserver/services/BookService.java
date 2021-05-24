package ecommerce.ecommerceserver.services;

import ecommerce.ecommerceserver.domain.Book;
import ecommerce.ecommerceserver.request.BookFiltersRequest;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface BookService {

    Book getBookById(UUID id);

    void saveBookRate(BigDecimal commentRate, UUID bookId);

    String deleteBookById(UUID id);

    Book saveBook(Book book);

    Book updateBook(Book book);

    Book setBookOrder(UUID bookId , Integer order);

    Book deleteBookOrder(UUID bookId);

    List<Book> getSearchedBooks(String search);

    List<Book> getAllBooks();

    List<Book> getFilteredBooks(BookFiltersRequest bookFiltersRequest);
}
