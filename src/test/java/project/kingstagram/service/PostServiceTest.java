package project.kingstagram.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.annotation.Rollback;
import org.springframework.web.multipart.MultipartFile;
import project.kingstagram.post.dto.PostDto;
import project.kingstagram.domain.Comment;
import project.kingstagram.domain.Post;
import project.kingstagram.post.dto.request.PostCreateForm;
import project.kingstagram.post.dto.request.PostUpdateDto;
import project.kingstagram.repository.CommentRepository;
import project.kingstagram.repository.PostRepository;
import project.kingstagram.repository.UsersRepository;
import project.kingstagram.post.service.PostService;

import javax.transaction.Transactional;

import static org.assertj.core.api.Assertions.*;


@SpringBootTest
@Transactional
class PostServiceTest {

    @Autowired
    PostService postService;
    @Autowired
    PostRepository postRepository;
    @Autowired
    UsersRepository usersRepository;
    @Autowired
    CommentRepository commentRepository;

    @BeforeEach
    void before(){
        postRepository.deleteAll();

//        PostDto dto = new PostDto();
//        dto.setImageUrl("/Users/file/file-store");
//        dto.setPostContent("테스트용 데이터1");

//        PostCreateForm dto = PostCreateForm.builder()
//                .postContent(new Multipart)
//                .imageUrl()
//                .build();
//        postService.savePost(dto);
//
//        Post post = Post.builder().build();
//        postRepository.save(post);
//
//        Comment comment = Comment.builder().commentContent("이제돼").build();
//        commentRepository.save(comment);
//
//        comment.add(post); //fk관계에 있어서는 연관관계 편의메서드를 사용해야한다!!
    }

    @Test
    @DisplayName("게시글 등록")
    void savePost() {

//        PostDto dto = new PostDto();
//        dto.setPostContent("aaa");
//        dto.setImageUrl("path/aaa/asg");
//        Long postId = postService.savePost(dto);
//
//        Post post = postRepository.findById(postId).get();
//        assertThat(post.getPostContent()).isEqualTo("aaa");

    }
//modifying , transactional - delete시 붙여야됨! -> jpa가 가지고있는 메소드 말고 커스텀할때 사용해야함!
    @Test
    @Rollback(false)
    @DisplayName("게시글 삭제")
    void deletePost(){
        long cnt = postRepository.count();
        postService.deletePost(2L, 1L);
        assertThat(postRepository.count()).isEqualTo(cnt-1);

        //문제 : 테스트마다 id값이 바뀌어서 테스트 코드를 어떻게 해야할지 모르겠다..ㅜ
    }

    @Test
    @Rollback(false)
    @DisplayName("게시글 수정")
    void updatePost(){
        PostUpdateDto dto = PostUpdateDto.builder()
                        .postId(1L)
                                .postContent("게시글입니다2")
                                        .build();
        postService.updatePost(dto, 1L);
        Post post = postRepository.findById(1L).get();
        assertThat(post.getPostContent()).isEqualTo("게시글입니다2");

    }
    @Test
    @DisplayName("게시글 조회")
    void getPost(){
        postService.getSinglePost(1L);
    }
}