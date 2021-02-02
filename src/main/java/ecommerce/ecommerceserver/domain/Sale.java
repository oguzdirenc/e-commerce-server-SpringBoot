package ecommerce.ecommerceserver.domain;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Min;
import java.util.UUID;

@Getter
@Setter
@RequiredArgsConstructor
@AllArgsConstructor
@Entity
@Builder
public class Sale {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID saleId;

    @Min(value = 0,message = "Discount rate cannot be negative")
    private Integer discountRate;
}
