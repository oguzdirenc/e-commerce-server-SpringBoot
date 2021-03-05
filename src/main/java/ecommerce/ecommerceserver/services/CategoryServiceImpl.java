package ecommerce.ecommerceserver.services;

import ecommerce.ecommerceserver.domain.Book;
import ecommerce.ecommerceserver.domain.Category;
import ecommerce.ecommerceserver.repositories.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;
    private final BookService bookService;

    @Override
    public Book setBookCategory(UUID bookId, List<Category> categoryList) {

        Book book = bookService.getBookById(bookId);

        for(Category category : categoryList){

           Optional<Category> category1 = categoryRepository.findByCategoryDescription(category.getCategoryDescription());

           category1.ifPresentOrElse((cat)->{
               cat.getBookCategoryList().add(book);
               categoryRepository.save(cat);
           }

           ,() -> {
               Category newCategory = new Category();
               newCategory.setCategoryDescription(category.getCategoryDescription());
               newCategory.getBookCategoryList().add(book);
               categoryRepository.save(newCategory);
           } );

        }

        return book ;
    }
}
