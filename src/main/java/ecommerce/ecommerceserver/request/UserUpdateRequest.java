package ecommerce.ecommerceserver.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserUpdateRequest {
    private String fullName;
    private Long phoneNumber;
    private  String address;
}
