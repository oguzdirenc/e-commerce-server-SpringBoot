package ecommerce.ecommerceserver.services;

import ecommerce.ecommerceserver.domain.ApplicationUser;
import ecommerce.ecommerceserver.domain.Book;
import ecommerce.ecommerceserver.domain.Comment;
import ecommerce.ecommerceserver.exceptions.NotFoundException;
import ecommerce.ecommerceserver.repositories.ApplicationUserRepository;
import ecommerce.ecommerceserver.repositories.BookRepository;
import ecommerce.ecommerceserver.repositories.CommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {

    private final CommentRepository commentRepository;
    private final ApplicationUserRepository applicationUserRepository;
    private final BookRepository bookRepository;
    private final BookService bookService;

    @Override
    public Comment getCommentsByBookId(UUID id) {
        Comment comment =  commentRepository
                .findById(id)
                .orElseThrow(() -> new NotFoundException("Comment Not Found"));

        return comment;
    }

    @Override
    public Comment saveComment(Comment comment, UUID userId,UUID bookId) {

        ApplicationUser user= applicationUserRepository
                .findById(userId)
                .orElseThrow(() -> new NotFoundException("User Not Found"));

        Book book = bookRepository.findById(bookId)
                .orElseThrow(() -> new NotFoundException("Book Not Found"));

        bookService.saveBookRate(comment.getRate(),book.getBookId());

        comment.setBook(book);
        comment.setUser(user);

        return commentRepository.save(comment);

    }

    @Override
    public String deleteCommentById(UUID id) {

        Comment comment= commentRepository.findById(id).orElseThrow(() -> new NotFoundException("Comment Not Found"));

        commentRepository.delete(comment);

        return "Comment Deleted";
    }
}
