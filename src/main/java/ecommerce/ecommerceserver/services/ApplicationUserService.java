package ecommerce.ecommerceserver.services;

import ecommerce.ecommerceserver.domain.ApplicationUser;
import ecommerce.ecommerceserver.domain.Book;

import java.util.Set;
import java.util.UUID;

public interface ApplicationUserService {

    ApplicationUser getUserById(UUID id);

    Set<Book> getFavoriteBooksByUserId(UUID id);

    ApplicationUser saveApplicationUser(ApplicationUser user);

    String deleteApplicationUserById(UUID id);
}