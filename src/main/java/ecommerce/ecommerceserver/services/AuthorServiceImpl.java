package ecommerce.ecommerceserver.services;

import ecommerce.ecommerceserver.domain.Author;
import ecommerce.ecommerceserver.domain.Book;
import ecommerce.ecommerceserver.exceptions.NotFoundException;
import ecommerce.ecommerceserver.repositories.AuthorRepository;
import ecommerce.ecommerceserver.repositories.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AuthorServiceImpl implements AuthorService {

    private final BookService bookService;
    private final AuthorRepository authorRepository;
    private final BookRepository bookRepository;

    @Override
    public Author getAuthorById(UUID id) {
        return null;
    }

    @Override
    public Book saveBookAuthors(UUID bookId, UUID authorId) {

        Book book1 = bookService.getBookById(bookId);

        Author author =  authorRepository.findById(authorId).orElseThrow(() -> new NotFoundException("NotFound"));

        author.getBooksList().add(book1);
       // book1.getAuthorsList().add(author);

//        for (Author author : authorList){
//            book1.getAuthorsList().add(author);
//            bookRepository.save(book1);
//        }

        //book1.setAuthorsList(authorList);

        return bookRepository.save(book1);
    }

    @Override
    public Author saveAuthor(Author author) {

        return authorRepository.save(author);
    }
}
