package ecommerce.ecommerceserver.controller;

import ecommerce.ecommerceserver.domain.ShoppingCart;
import ecommerce.ecommerceserver.services.BookService;
import ecommerce.ecommerceserver.services.MapValidationErrorService;
import ecommerce.ecommerceserver.services.ShoppingCardService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.UUID;

@RestController
@CrossOrigin
@RequiredArgsConstructor
@RequestMapping("/api/shoppingCart")
public class ShoppingCartController {

    private final MapValidationErrorService mapValidationErrorService;
    private final BookService bookService;
    private final ShoppingCardService shoppingCardService;

    @PostMapping("/addBook/{bookId}")
    public ResponseEntity<?> addBookToShoppingCart(@Valid @RequestBody ShoppingCart shoppingCart
            , BindingResult bindingResult
            , @PathVariable UUID bookId) {
        if (bindingResult != null) {
            mapValidationErrorService.mapValidationService(bindingResult);
        }

        shoppingCardService.addBookToCard(bookId,shoppingCart);

        return new ResponseEntity<>(shoppingCart, HttpStatus.OK);
    }

    @PostMapping("/save")
    public ResponseEntity<?> saveShoppingCart(@Valid @RequestBody ShoppingCart shoppingCart
           ,BindingResult bindingResult){
        if(bindingResult != null) mapValidationErrorService.mapValidationService(bindingResult);

        return new ResponseEntity<ShoppingCart>(shoppingCardService.saveShoppingCart(shoppingCart),HttpStatus.CREATED);
    }

    @GetMapping("/name/{name}")
    public ResponseEntity<?> getShoppingCartByName(@PathVariable String name){
        return new ResponseEntity<>(shoppingCardService.getShoppingCartByName(name),HttpStatus.OK) ;
    }

    @GetMapping("/Id/{shoppingCartId}")
    public ResponseEntity<?> getShoppingCartById(@PathVariable UUID shoppingCartId){
        return new ResponseEntity<>(shoppingCardService.getShoppingCartBookList(shoppingCartId),HttpStatus.OK);
    }

    @GetMapping({"/books/{shoppingCartId}"})
    public ResponseEntity<?> getShoppingCartBooks(@PathVariable UUID shoppingCartId){
        return new ResponseEntity<>(shoppingCardService.getShoppingCartBookList(shoppingCartId),HttpStatus.OK);
    }

    @GetMapping({"books"})
    public ResponseEntity<?> userShoppingCartBooks(){
        return new ResponseEntity<>(shoppingCardService.userShoppingCartBooks(),HttpStatus.OK);
    }


}
