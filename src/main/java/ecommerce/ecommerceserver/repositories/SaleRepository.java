package ecommerce.ecommerceserver.repositories;

import ecommerce.ecommerceserver.domain.Sale;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface SaleRepository extends JpaRepository<Sale, UUID> {
}
