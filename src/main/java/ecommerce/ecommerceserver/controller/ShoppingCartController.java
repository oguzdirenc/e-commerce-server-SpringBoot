package ecommerce.ecommerceserver.controller;

import ecommerce.ecommerceserver.domain.ApplicationUser;
import ecommerce.ecommerceserver.domain.Book;
import ecommerce.ecommerceserver.domain.ShoppingCart;
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
    @PostMapping("/addBook/{bookId}/{username}")
    public ResponseEntity<?> addBookToShoppingCart(
             BindingResult result
            , @PathVariable UUID bookId
            , @PathVariable String username) {

        ResponseEntity <?> errorMap = mapValidationErrorService.mapValidationService(result);
        if(result.hasFieldErrors()) return errorMap;


        return new ResponseEntity<>(shoppingCardService.addBookToCard(bookId,username), HttpStatus.OK);
    }

    //Attention
    @PostMapping("/removeBook/{bookId}/{username}")
    public ResponseEntity<?> removeBookFromShoppingCart(@PathVariable UUID bookId, @PathVariable String username){
        return new ResponseEntity<>(shoppingCardService.removeBookFromCard(bookId,username),HttpStatus.OK);
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

    //Attention
    @GetMapping({"books/{username}"})
    public ResponseEntity<?> userShoppingCartBooks(@PathVariable String username){
        return new ResponseEntity<>(shoppingCardService.userShoppingCart(username),HttpStatus.OK);
    }


}
