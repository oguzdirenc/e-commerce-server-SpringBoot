package ecommerce.ecommerceserver.domain;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Min;
import java.util.UUID;


@Getter
@Setter
@AllArgsConstructor
@RequiredArgsConstructor
@Builder
@Entity
public class BookOrder {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long bookOrderId;

    @Min(value = 0,message = "Cargo price cannot be negative")
    private Float cargoPrice;

    @Min(value = 0,message = "Total price cannot be negative")
    private Float orderTotalPrice;

    @OneToOne
    @JoinColumn(name = "shopping_cart_id")
    private ShoppingCart shoppingCart;

    @OneToOne
    @JoinColumn(name = "cargo_id")
    private Cargo cargo;
}
