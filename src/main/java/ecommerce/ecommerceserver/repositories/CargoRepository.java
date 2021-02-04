package ecommerce.ecommerceserver.repositories;

import ecommerce.ecommerceserver.domain.Cargo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CargoRepository extends JpaRepository<Cargo,Long> {
}
