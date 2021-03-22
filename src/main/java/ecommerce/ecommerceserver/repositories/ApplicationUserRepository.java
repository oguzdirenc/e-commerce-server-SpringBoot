package ecommerce.ecommerceserver.repositories;

import ecommerce.ecommerceserver.domain.ApplicationUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface ApplicationUserRepository extends CrudRepository<ApplicationUser,UUID> {

    ApplicationUser findByUsername(String username);
    ApplicationUser getByApplicationUserId(UUID userId);

}
