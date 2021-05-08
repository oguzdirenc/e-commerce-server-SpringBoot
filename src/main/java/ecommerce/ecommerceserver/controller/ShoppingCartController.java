package ecommerce.ecommerceserver.controller;


import ecommerce.ecommerceserver.domain.Book;
import ecommerce.ecommerceserver.domain.ShoppingCart;
import ecommerce.ecommerceserver.response.BookSizeResponse;
import ecommerce.ecommerceserver.response.TotalPriceResponse;
import ecommerce.ecommerceserver.services.ApplicationUserService;
import ecommerce.ecommerceserver.services.BookService;
import ecommerce.ecommerceserver.services.MapValidationErrorService;
import ecommerce.ecommerceserver.services.ShoppingCardService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.security.Principal;
import java.util.List;
import java.util.UUID;

@RestController
@CrossOrigin
@RequiredArgsConstructor
@RequestMapping("/api/shoppingCart")
public class ShoppingCartController {

    private final MapValidationErrorService mapValidationErrorService;
    private final ShoppingCardService shoppingCardService;

    //3
    @PostMapping("/addBook/{bookId}")
    public ResponseEntity<?> addBookToShoppingCart(
             @PathVariable UUID bookId
            , Principal principal) {

        return new ResponseEntity<>(shoppingCardService.addBookToCard(bookId, principal.getName()), HttpStatus.OK);
    }

    //2
    @GetMapping("/removeBook/{bookId}")
    public ResponseEntity<?> decreaseBookOrderFromShoppingCart(@PathVariable UUID bookId, Principal principal){
        return new ResponseEntity<>(shoppingCardService.decreaseBookOrderFromCard(bookId, principal.getName()),HttpStatus.OK);
    }


    //1
    @GetMapping("/books")
    public ResponseEntity<List<BookSizeResponse>> userShoppingCartBooks(Principal principal){
        return new ResponseEntity<>(shoppingCardService.userShoppingCart(principal.getName()),HttpStatus.OK);
    }

    //4
    @GetMapping("/totalPrice")
    public ResponseEntity<TotalPriceResponse> getTotalPrice(Principal principal){
        return new ResponseEntity<>(shoppingCardService.getTotalPrice(principal.getName()),HttpStatus.OK);
    }

    @PostMapping("/removeAll/{bookId}")
    public ResponseEntity<List<Book>> removeBookFromCart(Principal principal, @PathVariable UUID bookId){
        return new ResponseEntity<>(shoppingCardService.removeBookFromCart(principal.getName(), bookId),HttpStatus.OK);
    }


}
