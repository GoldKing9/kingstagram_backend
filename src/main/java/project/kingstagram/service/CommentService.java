package project.kingstagram.service;

import org.springframework.web.bind.annotation.RequestBody;
import project.kingstagram.dto.*;

import java.util.List;

public interface CommentService {
    Long saveComment(CommentDto commentDto);
    List<CommentInfo> getComments(GetCommentRequest request);
    void createComment(CreateCommentRequest request);
    void editComment(EditCommentRequest request);
    //void deleteComment(Long CommentId);
    void deleteComment(DeleteCommentRequest request);
}
