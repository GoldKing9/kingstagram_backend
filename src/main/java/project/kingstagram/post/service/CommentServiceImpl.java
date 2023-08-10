package project.kingstagram.post.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import project.kingstagram.domain.Comment;
import project.kingstagram.domain.Post;
import project.kingstagram.domain.Users;
import project.kingstagram.post.dto.response.CommentDto;
import project.kingstagram.post.dto.response.CommentInfo;
import project.kingstagram.post.dto.request.CreateCommentRequest;
import project.kingstagram.post.dto.request.EditCommentRequest;
import project.kingstagram.repository.CommentRepository;
import project.kingstagram.repository.PostRepository;
import project.kingstagram.repository.UsersRepository;

import java.time.LocalDateTime;
import java.util.List;

@Slf4j //로그확인
@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService{

    private final UsersRepository usersRepository;
    private final PostRepository postRepository;
    private final CommentRepository commentRepository;


    @Override
    @Transactional //반영시킬때
    public Long saveComment(CommentDto commentDto){
        Comment comment = commentDto.toEntity();
        Comment saveComment = commentRepository.save(comment);
        return saveComment.getCommentId();
    }
    @Override
    public List<CommentInfo> getComments(Long postId) {
        return commentRepository.findAllByPostId(postId);
    }

    @Override
    @Transactional
    public void createComment(CreateCommentRequest createCommentRequest,Long userId) {
        Users users = usersRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("해당하는 유저를 찾을 수 없습니다."));
        Post post = postRepository.findById(createCommentRequest.getPostId())
                .orElseThrow(() -> new RuntimeException("해당하는 글을 찾을 수 없습니다."));
        Comment comment = Comment.builder()
                .user(users)
                .post(post)
                .commentContent(createCommentRequest.getContent())
                .commentTime(LocalDateTime.now())
                .build();

        commentRepository.save(comment);
        comment.add(post);
    }
    @Override
    @Transactional //DB에 반영하기위해
    public void editComment(EditCommentRequest editCommentRequest,Long userId){

        Comment comment = commentRepository.findById(editCommentRequest.getCommentId())
                .orElseThrow(() -> new RuntimeException("수정할 권한이 없습니다."));
        comment.update(editCommentRequest.getContent());
    }

    @Override
    @Transactional
    public void deleteComment(Long commentId) {
        Comment comment = commentRepository.findById(commentId)
                .orElseThrow(() -> new RuntimeException("해당하는 댓글을 찾을 수 없습니다."));
        commentRepository.delete(comment);
    }

}
