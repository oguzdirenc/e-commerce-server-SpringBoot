package ecommerce.ecommerceserver.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.hibernate.validator.constraints.Range;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.UUID;

@Getter
@Setter
@RequiredArgsConstructor
@AllArgsConstructor
@Entity
@Builder
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(columnDefinition = "BINARY(16)")
    private UUID commentId;


    @NotBlank(message = "Comment description should not be blank")
    private String commentDescription;


    @Range(min = 0,max = 5,message = "Please enter a valid rate")
    private Integer rate;

    @ManyToOne
    ApplicationUser user ;

    @JsonIgnore
    @ManyToOne
    Book book;


}
