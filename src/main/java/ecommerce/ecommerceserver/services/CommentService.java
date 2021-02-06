package ecommerce.ecommerceserver.services;

import ecommerce.ecommerceserver.domain.Comment;

import java.util.UUID;

public interface CommentService {

    Comment getCommentsByBookId(UUID id);

    Comment saveComment(Comment comment,UUID userId,UUID bookId);

    String deleteCommentById(UUID id);
}
