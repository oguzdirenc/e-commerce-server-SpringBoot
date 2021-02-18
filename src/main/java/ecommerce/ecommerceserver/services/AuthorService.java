package ecommerce.ecommerceserver.services;

import ecommerce.ecommerceserver.domain.Author;
import ecommerce.ecommerceserver.domain.Book;


import java.util.Set;
import java.util.UUID;

public interface AuthorService {

    Author getAuthorById(UUID id);

    Book saveBookAuthors(UUID bookId, Set<String> authorName);

    Author saveAuthor(Author author);

}
