package ecommerce.ecommerceserver.exceptions;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class NotFoundExceptionResponse {

    @JsonProperty("Not Found")
    private String message;
}
