package ecommerce.ecommerceserver.controller;

import ecommerce.ecommerceserver.domain.Comment;
import ecommerce.ecommerceserver.services.CommentService;
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
@CrossOrigin
@RequestMapping("/comment")
public class CommentController {

    private final MapValidationErrorService mapValidationErrorService;
    private final CommentService commentService;

    @PostMapping("/new/{userId}/{bookId}")
    public ResponseEntity<?> addNewComment(@Valid @RequestBody Comment comment
            , BindingResult bindingResult
            , @PathVariable UUID userId
            ,@PathVariable UUID bookId){

        ResponseEntity<?> errorMap = mapValidationErrorService.mapValidationService(bindingResult);
        if(errorMap != null) return errorMap;

        Comment comment1 = commentService.saveComment(comment,userId,bookId);
        return new ResponseEntity<>(comment1, HttpStatus.CREATED);
    }

}
