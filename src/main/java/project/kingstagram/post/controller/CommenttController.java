package project.kingstagram.post.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.web.bind.annotation.*;
import project.kingstagram.post.dto.response.CommentInfo;
import project.kingstagram.post.dto.request.CreateCommentRequest;
import project.kingstagram.post.dto.request.EditCommentRequest;
import project.kingstagram.post.service.CommentService;


import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
public class CommenttController {
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
