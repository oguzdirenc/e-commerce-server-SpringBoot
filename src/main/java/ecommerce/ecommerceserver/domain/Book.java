package ecommerce.ecommerceserver.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;


import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.*;

@Getter
@Setter
@RequiredArgsConstructor
@AllArgsConstructor
@Entity
@Builder
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(columnDefinition = "BINARY(16)")
    private UUID bookId;

    private BigDecimal totalRate= BigDecimal.valueOf(0);
    private BigDecimal commentCount= BigDecimal.valueOf(0);
    private Integer orderSize=0;

    //@Digits(integer = 1,fraction = 1)
    private BigDecimal bookRate= BigDecimal.valueOf(0.0);

    @NotBlank(message = "Kitap adı gereklidir")
    private String bookName;

    private String publisherName;

    private String publishedDate;

    private String bookPdfDownloadLink;

    private String bookBuyLink;

    @NotNull(message = "Sayfa sayısı gereklidir")
    private Integer bookPage;

    //@NotNull(message = "Book price should not be blank")
    //@Min(value = 0,message = "Price cannot be negative")
    private BigDecimal bookPrice;

    //@NotNull(message = "Book stock should not be blank")
    private Long bookStock;

    @Lob
    private String bookDescription;


    //@NotNull(message = "Book image should not be blank")
    @Lob
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
    @JsonIgnore
    private List<ShoppingCart> bookShoppingCart = new ArrayList<>();

    @ManyToOne
    private Sale sale;
}
