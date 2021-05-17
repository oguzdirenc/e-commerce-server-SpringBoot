package ecommerce.ecommerceserver.services.impl;

import ecommerce.ecommerceserver.domain.ApplicationUser;
import ecommerce.ecommerceserver.domain.Book;
import ecommerce.ecommerceserver.domain.Comment;
import ecommerce.ecommerceserver.exceptions.NotFoundException;
import ecommerce.ecommerceserver.repositories.ApplicationUserRepository;
import ecommerce.ecommerceserver.repositories.BookRepository;
import ecommerce.ecommerceserver.repositories.CommentRepository;
import ecommerce.ecommerceserver.services.ApplicationUserService;
import ecommerce.ecommerceserver.services.BookService;
import ecommerce.ecommerceserver.services.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {

    private final CommentRepository commentRepository;
    private final ApplicationUserService applicationUserService;
    private final ApplicationUserRepository applicationUserRepository;
    private final BookRepository bookRepository;
    private final BookService bookService;

    @Override
    public Comment getCommentsByBookId(UUID id) {
        return   commentRepository
                .findById(id)
                .orElseThrow(() -> new NotFoundException("Comment Not Found"));

    }

    @Override
    public Comment saveComment(Comment comment, String username,UUID bookId) {

        var newComment = Comment.builder()
                .commentDescription(comment.getCommentDescription())
                .rate(comment.getRate()).build();

        ApplicationUser user= applicationUserService.getUserByUsername(username);

        Book book = bookRepository.findById(bookId)
                .orElseThrow(() -> new NotFoundException("Book Not Found"));


        bookService.saveBookRate(BigDecimal.valueOf(newComment.getRate()),book.getBookId());
        book.getBookCommentList().add(newComment);
      //  bookRepository.save(book);

//        user.getUserCommentList().add(newComment);
//        applicationUserRepository.save(user);

        newComment.setBook(book);
        newComment.setUser(user);


        applicationUserRepository.save(user);
        bookRepository.save(book);


        return commentRepository.save(newComment);

    }

    @Override
    public String deleteCommentById(UUID id) {

        Comment comment= commentRepository.findById(id).orElseThrow(() -> new NotFoundException("Comment Not Found"));

        commentRepository.delete(comment);

        return "Comment Deleted";
    }

    @Override
    public List<Comment> getBookComments(UUID bookId) {

        var book= bookService.getBookById(bookId);
        return book.getBookCommentList();
    }
}
