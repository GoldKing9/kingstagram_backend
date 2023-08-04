package project.kingstagram.service;

import org.apache.catalina.User;
import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import project.kingstagram.Dto.PostDto;
import project.kingstagram.domain.Post;
import project.kingstagram.domain.Users;
import project.kingstagram.repository.PostRespository;
import project.kingstagram.repository.UsersRepository;

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
    @Autowired
    UsersRepository usersRepository;

    @BeforeEach
    void before(){
        repository.deleteAll();

        PostDto dto = new PostDto();
        dto.setImageUrl("/Users/file/file-store");
        dto.setPostContent("테스트용 데이터1");
        service.savePost(dto);
    }


    @Test
    @DisplayName("게시글 등록")
    void savePost() {

        PostDto dto = new PostDto();
        dto.setPostContent("aaa");
        dto.setImageUrl("path/aaa/asg");
        Long postId = service.savePost(dto);

        Post post = repository.findById(postId).get();
        assertThat(post.getPostContent()).isEqualTo("aaa");

    }
    @Test
    @DisplayName("게시글 삭제")
    void deletePost(){

        long cnt = repository.count();
        service.deletePost(1L);
        assertThat(repository.count()).isEqualTo(cnt-1);

        //문제 : 테스트마다 id값이 바뀌어서 테스트 코드를 어떻게 해야할지 모르겠다..ㅜ
    }

    @Test
    @DisplayName("게시글 수정")
    void updatePost(){

        service.updatePost(1L, "게시글입니다2");
        Post post = repository.findById(1L).get();
        assertThat(post.getPostContent()).isEqualTo("게시글입니다2");

    }
}