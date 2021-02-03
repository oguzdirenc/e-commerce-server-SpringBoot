package ecommerce.ecommerceserver.domain;

import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.Future;
import javax.validation.constraints.Min;
import java.util.Date;
import java.util.UUID;

@Getter
@Setter
@Entity
@RequiredArgsConstructor
@AllArgsConstructor
@Builder
public class Cargo {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID cargoId;

    @Min(value = 0,message = "Cargo price cannot be negative")
    private Float cargoPrice;

    @Future(message = "Please enter a valid delivery time")
    private Date cargoDeliveryTime;

    @OneToOne(mappedBy = "cargo")
    BookOrder bookOrder;

}
