package ecommerce.ecommerceserver.repositories;

import ecommerce.ecommerceserver.domain.ShoppingCart;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ShoppingCartRepository extends JpaRepository<ShoppingCart,Long> {
}
