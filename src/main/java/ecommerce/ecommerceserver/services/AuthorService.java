package ecommerce.ecommerceserver.services;

import ecommerce.ecommerceserver.domain.Author;
import ecommerce.ecommerceserver.domain.Book;

import java.util.List;
import java.util.UUID;

public interface AuthorService {

    Author getAuthorById(UUID id);

    Book saveBookAuthors(UUID bookId, UUID authorId);

    Author saveAuthor(Author author);

}
