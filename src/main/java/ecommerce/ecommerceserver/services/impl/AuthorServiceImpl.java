package ecommerce.ecommerceserver.services.impl;

import ecommerce.ecommerceserver.domain.Author;
import ecommerce.ecommerceserver.domain.Book;
import ecommerce.ecommerceserver.exceptions.NotFoundException;
import ecommerce.ecommerceserver.repositories.AuthorRepository;
import ecommerce.ecommerceserver.repositories.BookRepository;
import ecommerce.ecommerceserver.services.AuthorService;
import ecommerce.ecommerceserver.services.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
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
    public Book saveBookAuthors(UUID bookId, List<Author> authorList) {

        Book book1 = bookRepository.findById(bookId).get();

        for (Author author : authorList) {

            if(author.getAuthorName() != null && !author.getAuthorName().equals("")){

                    Optional < Author > author1 = authorRepository.findByAuthorName(author.getAuthorName());

            author1.ifPresentOrElse((authorFound) -> {

                authorFound.getAuthorBooksList().add(book1);
                bookRepository.save(book1);
            }, () -> {
                if (author.getAuthorName() != null) {
                    Author newAuthor = new Author();
                    newAuthor.setAuthorName(author.getAuthorName());
                    if (author.getAuthorBio() != null) newAuthor.setAuthorBio(author.getAuthorBio());
                    System.out.println(author.getAuthorName());
                    if (author.getAuthorThumbnail() != null) newAuthor.setAuthorThumbnail(author.getAuthorThumbnail());
                    newAuthor.getAuthorBooksList().add(book1);
                    authorRepository.save(newAuthor);
                }
            });
            }}


        return bookRepository.findById(bookId).get();
    }

    @Override
    public Author saveAuthor(Author author) {

        return authorRepository.save(author);
    }
}
