package project.kingstagram.repository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import project.kingstagram.domain.Comment;
import project.kingstagram.domain.Post;
import project.kingstagram.domain.Users;
import project.kingstagram.post.dto.response.CommentDto;

import javax.transaction.Transactional;
import java.util.List;

@SpringBootTest
@Transactional
class CommentRepositoryTest {

    @Autowired
    CommentRepository commentRepository;
    @Autowired
    PostRepository postRepository;
    @Autowired
    UsersRepository usersRepository;

    @BeforeEach
    void init(){
        for(int i=1; i<=10; i++) {
            Users user =Users.builder()
                    .userDescription("hello123")
                    .userPw("123")
                    .userEmail("aaa@gmail.com")
                    .userName("경선")
                    .userNickname("aaa")
                    .build();

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
        }

    }


    @Test
    @Rollback(false)
    void findAllByPostId(){
        List<CommentDto> list = commentRepository.findAllByPostId(2L);
        for (CommentDto commentDto : list) {
            System.out.println(commentDto.toString());
        }
    }

}