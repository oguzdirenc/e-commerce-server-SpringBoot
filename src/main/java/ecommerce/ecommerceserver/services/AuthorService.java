package ecommerce.ecommerceserver.services;

import ecommerce.ecommerceserver.domain.Author;

import java.util.UUID;

public interface AuthorService {

    Author getAuthorById(UUID id);



}
