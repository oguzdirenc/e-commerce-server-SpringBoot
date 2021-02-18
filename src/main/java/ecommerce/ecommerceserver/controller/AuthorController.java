package ecommerce.ecommerceserver.controller;

import ecommerce.ecommerceserver.domain.Author;
import ecommerce.ecommerceserver.domain.Book;
import ecommerce.ecommerceserver.services.AuthorService;
import ecommerce.ecommerceserver.services.BookService;
import ecommerce.ecommerceserver.services.MapValidationErrorService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Set;
import java.util.UUID;

@RestController
@CrossOrigin
@RequiredArgsConstructor
@RequestMapping("/api/author")
public class AuthorController {

    private final MapValidationErrorService mapValidationErrorService;
    private final AuthorService authorService;
    private final BookService bookService;

    @PostMapping("/save")
    public ResponseEntity<?> saveAuthor(@Valid @RequestBody Author author,
                                        BindingResult bindingResult){
        if(bindingResult != null) mapValidationErrorService.mapValidationService(bindingResult);

        return new ResponseEntity<>(authorService.saveAuthor(author), HttpStatus.CREATED);

    }

    @PostMapping("/book/{bookId}")
    public ResponseEntity<?> saveBookAuthor(
                                            @PathVariable UUID bookId,
                                            @RequestBody Set<String> authorName){

       return new ResponseEntity<>(authorService.saveBookAuthors(bookId,authorName),HttpStatus.OK);

    }

}
