package ecommerce.ecommerceserver.controller;

import ecommerce.ecommerceserver.domain.Book;
import ecommerce.ecommerceserver.services.BookService;
import ecommerce.ecommerceserver.services.MapValidationErrorService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@CrossOrigin
@RequestMapping("/api/book")
public class BookController {

    private final BookService bookService;
    private final MapValidationErrorService mapValidationErrorService;

    @PostMapping("/save")
    public ResponseEntity<?> saveBook(@Valid @RequestBody Book book){

        /*ResponseEntity<?> errorMap= mapValidationErrorService.mapValidationService(result);
        if (result.hasFieldErrors()) return errorMap;*/

        return new ResponseEntity<>(bookService.saveBook(book), HttpStatus.CREATED);
    }

    @PostMapping("/update")
    public ResponseEntity<?> updateBook(@Valid @RequestBody Book book ,BindingResult result){

        if (result != null) mapValidationErrorService.mapValidationService(result);

        return new ResponseEntity<>(bookService.updateBook(book),HttpStatus.OK);
    }

    @PostMapping("/setOrder/{bookId}/{order}")
    public ResponseEntity<?> setBookOrder(@PathVariable UUID bookId , @PathVariable Integer order){
        return new ResponseEntity<>(bookService.setBookOrder(bookId,order),HttpStatus.OK);
    }

    @PostMapping("/delete/order/{bookId}")
    public  ResponseEntity<?> deleteBookOrder(@PathVariable UUID bookId){
        return new ResponseEntity<>(bookService.deleteBookOrder(bookId),HttpStatus.OK);
    }

    @GetMapping("/all")
    public ResponseEntity<?> getAllBooks(){
        List<Book> allBooks = bookService.getAllBooks();
        return new ResponseEntity<>(allBooks,HttpStatus.OK);
    }

    @DeleteMapping("delete/{bookId}")
    public ResponseEntity<?> deleteBook(@PathVariable UUID bookId){
        return new ResponseEntity<>(bookService.deleteBookById(bookId),HttpStatus.OK);
    }


}
