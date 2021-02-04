package ecommerce.ecommerceserver.repositories;

import ecommerce.ecommerceserver.domain.BookOrder;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookOrderRepository extends JpaRepository<BookOrder,Long> {
}
