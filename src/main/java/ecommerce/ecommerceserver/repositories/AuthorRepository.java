package ecommerce.ecommerceserver.repositories;

import ecommerce.ecommerceserver.domain.Author;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorRepository extends JpaRepository<Author,Long> {
}
