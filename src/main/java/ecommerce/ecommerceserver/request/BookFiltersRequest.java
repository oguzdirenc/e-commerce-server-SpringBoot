package ecommerce.ecommerceserver.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BookFiltersRequest {
    private String category;
    private Long maxPrice;
    private Long minPrice;
}
