package project.kingstagram.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import project.kingstagram.dto.CommentInfo;
import project.kingstagram.dto.CreateCommentRequest;
import project.kingstagram.dto.GetCommentRequest;
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
    public String createComment(@RequestBody CreateCommentRequest createCommentRequest){
        commentService.createComment(createCommentRequest);
        return "Success";
    }
}
