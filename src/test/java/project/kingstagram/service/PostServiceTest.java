package project.kingstagram.service;

import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import project.kingstagram.Dto.PostDto;
import project.kingstagram.domain.Post;
import project.kingstagram.repository.PostRespository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class PostServiceTest {

    @Autowired
    PostService service;
    @Autowired
    PostRespository repository;

    @BeforeEach
    void before(){
        repository.deleteAllInBatch();
    }

    @Test
    void savePost() {
        Long before = repository.count();

        PostDto dto = new PostDto();
        dto.setPostContent("aaa");
        dto.setImageUrl("path/aaa/asg");
        service.savePost(dto);


        Post post = repository.findById(1L).get();
        System.out.println(post.toString());
        assertThat(post.getPostContent()).isEqualTo("aaa");
        assertThat(repository.count()).isEqualTo(before+1);
    }
}