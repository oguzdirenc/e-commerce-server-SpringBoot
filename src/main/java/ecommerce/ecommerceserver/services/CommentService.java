package ecommerce.ecommerceserver.services;

import ecommerce.ecommerceserver.domain.Comment;

import java.util.List;
import java.util.UUID;

public interface CommentService {

    Comment getCommentsByBookId(UUID id);

    Comment saveComment(Comment comment,String username,UUID bookId);

    String deleteCommentById(UUID id);

    List<Comment> getBookComments(UUID bookId);
}
