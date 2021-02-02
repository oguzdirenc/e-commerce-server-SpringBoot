package ecommerce.ecommerceserver.domain;

import jdk.nashorn.api.tree.SimpleTreeVisitorES6;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import java.util.UUID;

@Getter
@Setter
@Entity
@RequiredArgsConstructor
@AllArgsConstructor
@Builder
public class Author {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID authorId;

    @NotBlank(message = "Author name should not be blank")
    private String authorName;

    @NotBlank(message = "Author image should not be blank")
    private String authorThumbnail;

    @NotBlank(message = "Author description field should not be blank")
    private String authorBio;

}
