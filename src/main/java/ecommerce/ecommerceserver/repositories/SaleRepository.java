package ecommerce.ecommerceserver.repositories;

import ecommerce.ecommerceserver.domain.Sale;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SaleRepository extends JpaRepository<Sale,Long> {
}
