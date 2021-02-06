package ecommerce.ecommerceserver.repositories;

import ecommerce.ecommerceserver.domain.Cargo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface CargoRepository extends JpaRepository<Cargo, UUID> {
}
