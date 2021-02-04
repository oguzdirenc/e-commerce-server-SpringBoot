package ecommerce.ecommerceserver.repositories;

import ecommerce.ecommerceserver.domain.ApplicationUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ApplicationUserRepository extends JpaRepository<ApplicationUser,Long> {
}
