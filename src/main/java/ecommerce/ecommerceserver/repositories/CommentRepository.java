package ecommerce.ecommerceserver.repositories;

import ecommerce.ecommerceserver.domain.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface CommentRepository extends JpaRepository<Comment, UUID> {
}
