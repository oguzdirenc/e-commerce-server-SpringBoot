package ecommerce.ecommerceserver.domain;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.util.*;

@Getter
@Setter
@RequiredArgsConstructor
@AllArgsConstructor
@Entity
@Builder
public class ApplicationUser {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID applicationUserId;

    @NotBlank(message = "Username should not be blank")
    @Email(message = "Username should be an e-mail")
    private String username;

    @NotBlank(message = "Name field should not be blank")
    private String fullName;

    @NotBlank(message = "Address field should not be blank")
    private String userAddress;

    @NotBlank(message = "Password field should not be blank")
    private String password;

    @Transient
    private String confirmPassword;

    @NotBlank(message = "Phone number field should not be blank")
    private Long userPhoneNumber;

    @OneToMany(mappedBy = "user",cascade = CascadeType.ALL)
    List<Comment> userCommentList = new ArrayList<>();

    @OneToMany(mappedBy = "user",cascade = CascadeType.ALL)
    Set<Book> favoriteBookSet = new HashSet<>();

    @OneToOne
    @JoinColumn(name = "shopping_cart_id")
    ShoppingCart shoppingCart;

}
