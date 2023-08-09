package project.kingstagram.post.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import project.kingstagram.repository.PostRepository;
import project.kingstagram.user.dto.UserProfilePostDTO;
import project.kingstagram.user.dto.response.PostIdAndImageUrlDTO;


import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class PostServiceTest {

    @Autowired
    PostService postService;
    @Autowired
    PostRepository postRepository;
    @Test
    void getMyPost() {
        Long userId = 11L;
        Pageable pageable = Pageable.ofSize(2);
        UserProfilePostDTO myPost = postService.getMyPost(userId, pageable);
        System.out.println(myPost.getTotalPages());
        System.out.println(myPost.toString());
//        System.out.println(postRepository.findCountPostByUserId(125L));

    }
}