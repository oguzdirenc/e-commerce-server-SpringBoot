package ecommerce.ecommerceserver.domain;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Min;
import java.util.UUID;

@Entity
@Getter
@Setter
@RequiredArgsConstructor
@AllArgsConstructor
@Builder
public class ShoppingCart {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID shoppingCartId;

    @Min(value = 0,message = "Total price cannot be negative")
    private Float totalPriceShoppingCart;

    @OneToOne
    private ApplicationUser user;


}
