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
import java.security.Principal;
import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@CrossOrigin
@RequestMapping("/api/comment")
public class CommentController {

    private final MapValidationErrorService mapValidationErrorService;
    private final CommentService commentService;

    @PostMapping("/new/{bookId}")
    public ResponseEntity<?> addNewComment(@Valid @RequestBody Comment comment
            , BindingResult bindingResult
            , Principal principal
            , @PathVariable UUID bookId){

        ResponseEntity<?> errorMap = mapValidationErrorService.mapValidationService(bindingResult);
        if(errorMap != null) return errorMap;

        Comment comment1 = commentService.saveComment(comment,principal.getName(),bookId);
        return new ResponseEntity<>(comment1, HttpStatus.CREATED);
    }

    @GetMapping("/book/{bookId}")
    public ResponseEntity<List<Comment>> getBookComments(@PathVariable UUID bookId){
        return new ResponseEntity<>(commentService.getBookComments(bookId),HttpStatus.OK);
    }

}
