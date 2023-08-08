package project.kingstagram.repository;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;
import project.kingstagram.domain.Comment;
import project.kingstagram.domain.Like;
import project.kingstagram.domain.Post;
import project.kingstagram.domain.Users;
import project.kingstagram.dto.postGet.MiniDto;
import project.kingstagram.dto.postGet.PostOneDto;

@SpringBootTest
@Transactional
class PostRepositoryTest {

    @Autowired CommentRepository commentRepository;
    @Autowired PostRepository postRepository;
    @Autowired UsersRepository usersRepository;
    @Autowired LikeRepository likeRepository;

    @BeforeEach
    void init(){
        for(int i=1; i<=10; i++) {
            Users user = new Users();
            user.setUserNickname("k._.");
            user.setUserName("경선" + i);
            user.setUserPw("123");
            user.setUserEmail("aaa@gmail.com");

            Post post = Post.builder()
                    .postContent("내용" + i)
                    .user(user).build();

            usersRepository.save(user);
            postRepository.save(post);

            Comment comment = Comment.builder()
                    .commentContent(i+ "번째 게시물의 댓글")
                    .user(user)
                    .build();

            Comment comment2 = Comment.builder()
                    .commentContent(i+ "번째 게시물의 댓글" + i)
                    .user(user)
                    .build();

            commentRepository.save(comment);
            commentRepository.save(comment2);

            comment.add(post); //연관관계 잊지말자 제발...
            comment2.add(post);

            Like like = Like.builder()
                    .post(post)
                    .user(user)
                    .build();

            likeRepository.save(like);
        }
        Like like = Like.builder()
                .post(postRepository.findById(11L).get())
                .build();

        likeRepository.save(like);
    }

    @Test
    @Rollback(value = false)
    void test(){

        MiniDto miniDto = postRepository.findAllByPostId(1L);
        MiniDto miniDto2 = postRepository.findAllByPostId(2L);
            System.out.println(miniDto.toString());
            System.out.println(miniDto2.toString());

    }

    @Test
    @Rollback(value = true)
    void 사용자_포스트_카운트_테스트(){
        Integer res = postRepository.findPostCountByUserId(11L);
        System.out.println(res);
        Assertions.assertEquals(res,4);
    }
}