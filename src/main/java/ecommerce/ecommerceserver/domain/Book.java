package ecommerce.ecommerceserver.domain;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.UUID;

@Entity
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID bookId;

    @NotBlank(message = "Book name should not be blank")
    private String bookName;

    @NotNull(message = "Book page field should not be blank")
    private Integer bookPage;

    @NotNull(message = "Book price should not be blank")
    @Min(value = 0,message = "Price cannot be negative")
    private BigDecimal bookPrice;

    @NotNull(message = "Book stock should not be blank")
    private Long bookStock;

    @NotBlank(message = "Book description should not be blank")
    private String bookDescription;

    @Lob
    @NotNull(message = "Book image should not be blank")
    private String bookThumbnail;
}
