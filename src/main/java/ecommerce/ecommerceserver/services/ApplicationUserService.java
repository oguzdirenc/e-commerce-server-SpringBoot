package ecommerce.ecommerceserver.services;

import ecommerce.ecommerceserver.domain.ApplicationUser;
import ecommerce.ecommerceserver.domain.Book;
import ecommerce.ecommerceserver.domain.ShoppingCart;

import java.util.Set;
import java.util.UUID;

public interface ApplicationUserService {

    ApplicationUser getUserByUsername(String username);

    ShoppingCart getShoppingCartByUsername(String username);

    ApplicationUser saveUser(ApplicationUser newUser);
}
