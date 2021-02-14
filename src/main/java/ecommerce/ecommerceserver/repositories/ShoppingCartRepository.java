package ecommerce.ecommerceserver.repositories;

import ecommerce.ecommerceserver.domain.ShoppingCart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;
import java.util.UUID;

public interface ShoppingCartRepository extends JpaRepository<ShoppingCart, UUID> {

    @Query("select x from ShoppingCart x where x.shoppingCartName =:name")
    Optional<ShoppingCart> getShoppingCartByName(String name);
}
