package ecommerce.ecommerceserver.services.impl;

import ecommerce.ecommerceserver.domain.Book;
import ecommerce.ecommerceserver.domain.Category;
import ecommerce.ecommerceserver.exceptions.NotFoundException;
import ecommerce.ecommerceserver.repositories.BookRepository;
import ecommerce.ecommerceserver.request.BookFiltersRequest;
import ecommerce.ecommerceserver.services.BookService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.stream.Collectors;

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
    public void saveBookRate(BigDecimal commentRate, UUID bookId) {
        Book book= getBookById(bookId);
        book.setTotalRate(book.getTotalRate().add(commentRate));
        book.setCommentCount(book.getCommentCount().add(BigDecimal.valueOf(1)));
        book.setBookRate(book.getTotalRate().divide(book.getCommentCount(),BigDecimal.ROUND_HALF_UP));
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
    public List<Book> getSearchedBooks(String search) {

        return bookRepository.findByBookName(search);
    }


    @Override
    public List<Book> getAllBooks() {

        return bookRepository.findAll();
    }

    public boolean isCategoryInclude(List<Category> categoryList, String filter) {
        boolean isInclude = false;

        if (filter.equals("Kategori") || filter.equals("")) {
            isInclude = true;
        } else {
            for (Category category : categoryList) {
                if (category.getCategoryDescription().equals(filter)) {
                    isInclude = true;
                    break;
                }
            }
        }

        return isInclude;
    }

    public boolean isInPriceRange(Book book, BookFiltersRequest bookFiltersRequest){
        return book.getBookPrice().longValue() <= bookFiltersRequest.getMaxPrice() && book.getBookPrice().longValue() >= bookFiltersRequest.getMinPrice();
    }

    @Override
    public List<Book> getFilteredBooks(BookFiltersRequest bookFiltersRequest) {
        var bookList = getAllBooks();

        if(bookFiltersRequest.getMaxPrice()==null)bookFiltersRequest.setMaxPrice((long) 999.99);
        if(bookFiltersRequest.getMinPrice()==null)bookFiltersRequest.setMinPrice((long) 0.00);


       return bookList.stream()
                .filter(book -> isCategoryInclude(book.getCategoryBooksList(),bookFiltersRequest.getCategory()))
                .filter(book -> isInPriceRange(book,bookFiltersRequest))
                .collect(Collectors.toList());
    }
}