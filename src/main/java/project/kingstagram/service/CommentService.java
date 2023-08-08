package project.kingstagram.service;

import project.kingstagram.dto.*;
import project.kingstagram.dto.request.CreateCommentRequest;
import project.kingstagram.dto.request.EditCommentRequest;

import java.util.List;

public interface CommentService {
    Long saveComment(CommentDto commentDto);
    List<CommentInfo> getComments(Long postId);
    void createComment(CreateCommentRequest request, Long userId);
    void editComment(EditCommentRequest request, Long userId);
    //void deleteComment(Long CommentId);
    void deleteComment(Long commentId);
}
