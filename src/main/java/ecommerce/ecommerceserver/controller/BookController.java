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
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/book")
public class BookController {

    private final BookService bookService;
    private final MapValidationErrorService mapValidationErrorService;

    @PostMapping("/save")
    public ResponseEntity<?> saveBook(@Valid @RequestBody Book book , BindingResult result){
        if (result != null) mapValidationErrorService.mapValidationService(result);

        return new ResponseEntity<>(bookService.saveBook(book), HttpStatus.CREATED);
    }

    @GetMapping("/{bookId}")
    public ResponseEntity<?> getBook(@PathVariable UUID bookId){


        return new ResponseEntity<Book>(bookService.getBookById(bookId),HttpStatus.OK);
    }
}
