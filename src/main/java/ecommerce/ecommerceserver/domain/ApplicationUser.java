package ecommerce.ecommerceserver.domain;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.util.UUID;

@Entity
public class ApplicationUser {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID applicationUserId;

    @NotBlank(message = "Username should not be blank")
    @Email(message = "Username should be an e-mail")
    private String username;

    @NotBlank(message = "Name field should not be blank")
    private String fullName;

    @NotBlank(message = "Adress field should not be blank")
    private String userAddress;

    @NotBlank(message = "Password field should not be blank")
    private String password;

    @Transient
    private String confirmPassword;

    @NotBlank(message = "Phone number field should not be blank")
    private Long userPhoneNumber;
}
