package ecommerce.ecommerceserver.services;

import ecommerce.ecommerceserver.domain.Book;
import ecommerce.ecommerceserver.exceptions.NotFoundException;
import ecommerce.ecommerceserver.repositories.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.UUID;
@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;

    @Override
    public Book getBookById(UUID id) {

        Book book = bookRepository.findById(id).orElseThrow(() -> new NotFoundException("Book Not Found"));
        return book;
    }



    @Override
    public void saveBookRate(Integer commentRate, UUID bookId) {
        Book book= getBookById(bookId);
        book.setTotalRate(book.getTotalRate()+commentRate);
        book.setCommentCount(book.getCommentCount()+1);
        book.setBookRate((float) (book.getTotalRate()/book.getCommentCount()));
    }

    @Override
    public String deleteBookById(UUID id) {

        Book book = bookRepository.findById(id).orElseThrow(() -> new NotFoundException("Book Not Found"));

        bookRepository.delete(book);

        return "Book Deleted";
    }

    @Override
    public Book saveBook(Book book) {

        return bookRepository.save(book);
    }

    @Override
    public Book updateBook(Book book) {

        Book book1 = getBookById(book.getBookId());

        book1.setBookPdfDownloadLink(book.getBookPdfDownloadLink());
        book1.setBookBuyLink(book.getBookBuyLink());
        book1.setBookPrice(book.getBookPrice());
        book1.setBookStock(book.getBookStock());

        return bookRepository.save(book1);
    }

    @Override
    public Boolean addToShoppingCart(UUID bookId) {
        Book book = getBookById(bookId);
        if(book.getOrderSize() == 0) {
            book.setOrderSize(1);
            bookRepository.save(book);
            return true;
        }
        return false;
    }

    @Override
    public Book getBookByName(String bookName) {
        return bookRepository.findByBookName(bookName).orElseThrow(() -> new NotFoundException("Book not found"));
    }

    @Override
    public List<Book> getAllBooks() {

        return bookRepository.findAll();
    }
}
