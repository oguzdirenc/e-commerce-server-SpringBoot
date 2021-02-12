package ecommerce.ecommerceserver.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Digits;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@RequiredArgsConstructor
@AllArgsConstructor
@Entity
@Builder
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID bookId;

    private Integer totalRate=0;
    private Integer commentCount=0;

    @Digits(integer = 1,fraction = 1)
    private Float bookRate=0.0F;

    @NotBlank(message = "Book name should not be blank")
    private String bookName;

    //@NotNull(message = "Book page field should not be blank")
    private Integer bookPage;

    //@NotNull(message = "Book price should not be blank")
    @Min(value = 0,message = "Price cannot be negative")
    private BigDecimal bookPrice;

    //@NotNull(message = "Book stock should not be blank")
    private Long bookStock;

    //@NotBlank(message = "Book description should not be blank")
    private String bookDescription;

    @Lob
    //@NotNull(message = "Book image should not be blank")
    private String bookThumbnail;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "application_user_id")
    private ApplicationUser user ;

    @OneToMany(mappedBy = "book",cascade = CascadeType.ALL)
    private List<Comment> bookCommentList = new ArrayList<>();

    @ManyToMany(mappedBy = "bookCategoryList",cascade = CascadeType.ALL)
    private List<Category> categoryBooksList =new ArrayList<>();

    @ManyToMany(mappedBy = "authorBooksList",cascade = CascadeType.ALL)
    private List<Author> bookAuthorsList =new ArrayList<>();

    @ManyToMany(mappedBy = "shoppingCartBooks",cascade = CascadeType.ALL)
    private List<ShoppingCart> bookShoppingCart = new ArrayList<>();

    @ManyToOne
    private Sale sale;
}
