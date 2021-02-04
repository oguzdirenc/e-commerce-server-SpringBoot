package ecommerce.ecommerceserver.repositories;

import ecommerce.ecommerceserver.domain.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book,Long> {
}
