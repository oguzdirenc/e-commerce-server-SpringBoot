package ecommerce.ecommerceserver.repositories;

import ecommerce.ecommerceserver.domain.Author;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface AuthorRepository extends JpaRepository<Author, UUID> {

    Optional<Author> findByAuthorName(String authorName);

}
