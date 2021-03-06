package ecommerce.ecommerceserver.services;

import ecommerce.ecommerceserver.domain.Author;
import ecommerce.ecommerceserver.domain.Book;
import ecommerce.ecommerceserver.exceptions.NotFoundException;
import ecommerce.ecommerceserver.repositories.AuthorRepository;
import ecommerce.ecommerceserver.repositories.BookRepository;
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
            Optional<Author> author1 = authorRepository.findByAuthorName(author.getAuthorName());

           author1.ifPresentOrElse((authorFound) ->{

                            authorFound.getBooksAuthorList().add(book1);
                            bookRepository.save(book1);
                            },() -> {
                            if(author.getAuthorName() != null){
                                Author newAuthor = new Author();
                                newAuthor.setAuthorName(author.getAuthorName());
                                if(author.getAuthorBio() != null) newAuthor.setAuthorBio(author.getAuthorBio());
                                if(author.getAuthorThumbnail() != null) newAuthor.setAuthorThumbnail(author.getAuthorThumbnail());
                                newAuthor.getBooksAuthorList().add(book1);
                                authorRepository.save(newAuthor);}
                            });
            }


        return book1;
    }

    @Override
    public Author saveAuthor(Author author) {

        return authorRepository.save(author);
    }
}
