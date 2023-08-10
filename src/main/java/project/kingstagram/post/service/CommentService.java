package project.kingstagram.post.service;

import org.springframework.stereotype.Service;
import project.kingstagram.post.dto.response.CommentDto;
import project.kingstagram.post.dto.response.CommentInfo;
import project.kingstagram.post.dto.request.CreateCommentRequest;
import project.kingstagram.post.dto.request.EditCommentRequest;

import java.util.List;
@Service
public interface CommentService {
    Long saveComment(CommentDto commentDto);
    List<CommentInfo> getComments(Long postId);
    void createComment(CreateCommentRequest request, Long userId);
    void editComment(EditCommentRequest request, Long userId);
    //void deleteComment(Long CommentId);
    void deleteComment(Long commentId);
}
