package project.kingstagram.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;
import project.kingstagram.domain.Comment;
import project.kingstagram.domain.Post;
import project.kingstagram.dto.CommentDto;
import project.kingstagram.repository.CommentRepository;
import project.kingstagram.repository.UsersRepository;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Transactional
public class CommentServiceTest {
    @Autowired
    CommentService commentservice;
    @Autowired
    CommentRepository commentRepository;
    @Autowired
    UsersRepository usersRepository;
    @BeforeEach
    void before(){
        commentRepository.deleteAll();

//        CommentDto commentDto = new CommentDto();
//        commentDto.setCommentContent("테스트입니다");
        Comment example = Comment.builder()
                .commentId(1L)
                .commentContent("example")
                .build();
        commentRepository.save(example);
    }
    @Test
    @DisplayName("댓글 등록")
    void savePost() {

        CommentDto dto = new CommentDto();
        dto.setCommentContent("aaa");;
        Long commentId = commentservice.saveComment(dto);

        Comment comment = commentRepository.findById(commentId).get();
        assertThat(comment.getCommentContent()).isEqualTo("aaa");

    }
}
