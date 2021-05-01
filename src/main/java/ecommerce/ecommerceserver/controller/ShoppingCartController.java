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
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@RestController
@CrossOrigin
@RequiredArgsConstructor
@RequestMapping("/api/shoppingCart")
public class ShoppingCartController {

    private final MapValidationErrorService mapValidationErrorService;
    private final BookService bookService;
    private final ShoppingCardService shoppingCardService;
    private final ApplicationUserService applicationUserService;

    //Attention
    @PostMapping("/addBook/{bookId}")
    public ResponseEntity<?> addBookToShoppingCart(
             @PathVariable UUID bookId
            , Principal principal) {


        return new ResponseEntity<>(shoppingCardService.addBookToCard(bookId, principal.getName()), HttpStatus.OK);
    }

    //Attention
    @GetMapping("/removeBook/{bookId}")
    public ResponseEntity<?> removeBookFromShoppingCart(@PathVariable UUID bookId, Principal principal){
        return new ResponseEntity<>(shoppingCardService.decreaseBookOrderFromCard(bookId, principal.getName()),HttpStatus.OK);
    }

    @PostMapping("/save")
    public ResponseEntity<?> saveShoppingCart(@Valid @RequestBody List<Book> bookList
           , BindingResult bindingResult, Principal principal){

        ResponseEntity<?> errorMap = mapValidationErrorService.mapValidationService(bindingResult);
        if(bindingResult.hasFieldErrors()) return errorMap;

        return new ResponseEntity<ShoppingCart>(shoppingCardService.saveShoppingCart(bookList,principal.getName()),HttpStatus.CREATED);
    }



    @GetMapping("/Id/{shoppingCartId}")
    public ResponseEntity<?> getShoppingCartById(@PathVariable UUID shoppingCartId){
        return new ResponseEntity<>(shoppingCardService.getShoppingCartBookList(shoppingCartId),HttpStatus.OK);
    }

    @GetMapping({"/books/{shoppingCartId}"})
    public ResponseEntity<?> getShoppingCartBooks(@PathVariable UUID shoppingCartId){
        return new ResponseEntity<>(shoppingCardService.getShoppingCartBookList(shoppingCartId),HttpStatus.OK);
    }


    @GetMapping("/books")
    public ResponseEntity<List<BookSizeResponse>> userShoppingCartBooks(Principal principal){
        return new ResponseEntity<>(shoppingCardService.userShoppingCart(principal.getName()),HttpStatus.OK);
    }

    @GetMapping("/totalPrice")
    public ResponseEntity<TotalPriceResponse> getTotalPrice(Principal principal){
        return new ResponseEntity<>(shoppingCardService.getTotalPrice(principal.getName()),HttpStatus.OK);
    }


}
