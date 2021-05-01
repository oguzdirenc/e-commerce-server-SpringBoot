package ecommerce.ecommerceserver.response;

import ecommerce.ecommerceserver.domain.Book;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BookSizeResponse {

   private Book book;
   private Integer count;

}
