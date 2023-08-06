package project.kingstagram.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import project.kingstagram.domain.Comment;
import project.kingstagram.domain.Post;
import project.kingstagram.domain.Users;
import project.kingstagram.dto.CommentDto;
import project.kingstagram.dto.CommentInfo;
import project.kingstagram.dto.CreateCommentRequest;
import project.kingstagram.dto.GetCommentRequest;
import project.kingstagram.repository.CommentRepository;
import project.kingstagram.repository.PostRepository;
import project.kingstagram.repository.UsersRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Slf4j
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
    public List<CommentInfo> getComments(GetCommentRequest request) {
        return null;
    }

    @Override
    public void createComment(CreateCommentRequest createCommentRequest) {
        Users users = usersRepository.findById(createCommentRequest.getUserId())
                .orElseThrow(() -> new RuntimeException("해당하는 유저를 찾을 수 없습니다."));

        Comment comment = Comment.builder()
                .user(users)
                .commentContent(createCommentRequest.getContent())
                .commentTime(LocalDateTime.now())
                .build();

        commentRepository.save(comment);
    }

//    @Override
//    public List<CommentInfo> getComments(GetCommentRequest request) {
//        Post findPost = postRepository.findById(request.getPostId())
//                .orElseThrow(() -> new RuntimeException("해당하는 게시글을 찾을 수 없습니다."));
//        List<Comment> result = commentRepository.findAllByPost(findPost);
//
//        for (Comment comment : result) {
//            comment.getUser().get
//        }
//
//    }
}
