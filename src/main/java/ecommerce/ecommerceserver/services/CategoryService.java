package ecommerce.ecommerceserver.services;

import ecommerce.ecommerceserver.domain.Book;
import ecommerce.ecommerceserver.domain.Category;

import java.util.List;
import java.util.UUID;

public interface CategoryService {

    Book setBookCategory(UUID bookId, List<Category> categoryList);

}
