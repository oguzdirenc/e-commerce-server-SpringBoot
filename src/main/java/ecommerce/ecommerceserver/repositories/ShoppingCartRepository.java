package ecommerce.ecommerceserver.repositories;

import ecommerce.ecommerceserver.domain.ShoppingCart;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ShoppingCartRepository extends JpaRepository<ShoppingCart, UUID> {
}
