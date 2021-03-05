package ecommerce.ecommerceserver.controller;

import com.fasterxml.jackson.annotation.JsonIgnore;
import ecommerce.ecommerceserver.domain.Book;
import ecommerce.ecommerceserver.domain.Category;
import ecommerce.ecommerceserver.services.BookService;
import ecommerce.ecommerceserver.services.CategoryService;
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
@RequestMapping("/api/category")
public class CategoryController {

    private final CategoryService categoryService;
    private final MapValidationErrorService mapValidationErrorService;

    @PostMapping("/setCategory/{bookId}")
    ResponseEntity<?> setBookCategory(@Valid @RequestBody List<Category> categoryList,
                                      BindingResult result ,
                                      @PathVariable UUID bookId){
        ResponseEntity<?> errorMap = mapValidationErrorService.mapValidationService(result);
        if(errorMap != null) return errorMap;

        return new  ResponseEntity<>(categoryService.setBookCategory(bookId,categoryList), HttpStatus.OK);
    }

}
