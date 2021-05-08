package ecommerce.ecommerceserver.services.impl;

import ecommerce.ecommerceserver.domain.Book;
import ecommerce.ecommerceserver.exceptions.NotFoundException;
import ecommerce.ecommerceserver.repositories.BookRepository;
import ecommerce.ecommerceserver.services.BookService;
import lombok.RequiredArgsConstructor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.UUID;

@Service
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;
    
    
    @Autowired
    public BookServiceImpl(BookRepository bookRepository) {
		super();
		this.bookRepository = bookRepository;
	}



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
    public Book setBookOrder(UUID bookId, Integer order) {

        Book book = getBookById(bookId);

        book.setOrderSize(order);

        return bookRepository.save(book);
    }

    @Override
    public Book deleteBookOrder(UUID bookId) {

        Book book = getBookById(bookId);

        book.setOrderSize(0);

        return bookRepository.save(book);
    }


    @Override
    public List<Book> getAllBooks() {

        return bookRepository.findAll();
    }
}
