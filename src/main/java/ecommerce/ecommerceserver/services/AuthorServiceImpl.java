package ecommerce.ecommerceserver.services;

import ecommerce.ecommerceserver.domain.Author;
import ecommerce.ecommerceserver.domain.Book;
import ecommerce.ecommerceserver.exceptions.NotFoundException;
import ecommerce.ecommerceserver.repositories.AuthorRepository;
import ecommerce.ecommerceserver.repositories.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AuthorServiceImpl implements AuthorService {

    private final BookService bookService;
    private final AuthorRepository authorRepository;
    private final BookRepository bookRepository;

    @Override
    public Author getAuthorById(UUID authorId) {
        return authorRepository.findById(authorId).orElseThrow(() -> new NotFoundException("Author not found"));
    }

    @Override
    public Book saveBookAuthors(UUID bookId, Set<String> authorSet) {

        Book book1 = bookRepository.findById(bookId).get();

        for (String authorName : authorSet) {
            authorRepository.findByAuthorName(authorName).
                    ifPresentOrElse((authorFound) -> book1.getAuthorsList().add(authorFound),
                            () -> {
                                Author author = new Author();
                                author.setAuthorName(authorName);
                                author.getBooksList().add(book1);
                                authorRepository.save(author);
                            });
        }

        bookRepository.save(book1);
        return book1;
    }

    @Override
    public Author saveAuthor(Author author) {

        return authorRepository.save(author);
    }
}
