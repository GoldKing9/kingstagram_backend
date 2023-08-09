package project.kingstagram.post.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import project.kingstagram.post.service.LikeService;
import javax.servlet.http.HttpSession;

@RestController
@RequiredArgsConstructor
public class LikeController {
    private final LikeService likeService;

    @PostMapping("/api/{postId}like")
    public String likePost(@PathVariable Long postId, @SessionAttribute Long userId) {
        likeService.likes(postId, userId);
        return "like";
    }

    @DeleteMapping("/api/{postId}unlike")
    public String unlikePost(@PathVariable Long postId, @SessionAttribute Long userId) {
        likeService.unLikes(postId, userId);
        return "delete";
    }
}
