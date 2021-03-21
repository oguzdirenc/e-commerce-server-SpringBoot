package ecommerce.ecommerceserver.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.util.Date;
import java.util.UUID;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private UUID userId;

    @Email(message = "Kullanıcı adı e-mail adresi olmalıdır")
    @NotBlank(message = "Kullanıcı adı gereklidir")
    @Column(unique = true)
    private String username;

    @NotBlank(message = "Adınızı giriniz")
    private String fullName;

    @NotBlank(message = "Şifre gereklidir")
    private String password;

    @Transient
    private String confirmPassword;

    private Date createdAt;
    private Date updatedAt;

    @PrePersist
    protected void onCreate() {this.createdAt = new Date();}

    @PreUpdate
    protected void onUpdate() {this.updatedAt = new Date();}
}
