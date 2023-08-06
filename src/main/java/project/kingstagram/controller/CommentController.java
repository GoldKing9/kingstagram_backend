package project.kingstagram.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import project.kingstagram.dto.*;
import project.kingstagram.service.CommentService;
import project.kingstagram.service.CommentServiceImpl;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class CommentController {
    private final CommentService commentService;

    @GetMapping("/comment")
    public List<CommentInfo> getComments(@RequestBody GetCommentRequest request) {
        return commentService.getComments(request);
    }

    @PostMapping("/comment")
    public String createComment(@RequestBody CreateCommentRequest createCommentRequest) {
        commentService.createComment(createCommentRequest);
        return "Success";
    }

    @PutMapping("/comment")
    public String updateComment(@RequestBody EditCommentRequest editCommentRequest) {
        commentService.editComment(editCommentRequest);
        return "Update";
    }
    @DeleteMapping("/comment")
    public String deleteComment(@RequestBody DeleteCommentRequest deleteCommentRequest){
        commentService.deleteComment(deleteCommentRequest);
        return "Delete";
    }
//    @DeleteMapping("/comment/{commentId}")
//    public String deleteComment(@PathVariable Long commentId){
//        commentService.deleteComment(commentId);
//        return "Delete";
//    }
}
