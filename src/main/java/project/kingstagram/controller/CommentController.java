package project.kingstagram.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.web.bind.annotation.*;
import project.kingstagram.dto.*;
import project.kingstagram.dto.request.CreateCommentRequest;
import project.kingstagram.dto.request.EditCommentRequest;
import project.kingstagram.service.CommentService;


import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
public class CommentController {
    private final CommentService commentService;

    @GetMapping("api/comment/{postId}")
    public List<CommentInfo> getComments(@PathVariable Long postId) {
        log.info("request = {}", postId);
        return commentService.getComments(postId);
    }
    @PostMapping("/api/comment")
    public String createComment(@RequestBody CreateCommentRequest createCommentRequest, @SessionAttribute Long userId) {
        commentService.createComment(createCommentRequest,userId);
        return "Success";
    }
    @PutMapping("/api/comment")
    public String updateComment(@RequestBody EditCommentRequest editCommentRequest, @SessionAttribute Long userId) {
        commentService.editComment(editCommentRequest,userId);
        return "Update";
    }
    @DeleteMapping("/api/comment/{commentId}")
    public String deleteComment(@PathVariable Long commentId,@SessionAttribute Long userId){
        commentService.deleteComment(commentId);
        return "Delete";
    }

}
