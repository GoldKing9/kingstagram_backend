package project.kingstagram.service;

import org.springframework.web.bind.annotation.RequestBody;
import project.kingstagram.dto.CommentDto;
import project.kingstagram.dto.CommentInfo;
import project.kingstagram.dto.CreateCommentRequest;
import project.kingstagram.dto.GetCommentRequest;

import java.util.List;

public interface CommentService {
    Long saveComment(CommentDto commentDto);
    List<CommentInfo> getComments(GetCommentRequest request);
    void createComment(CreateCommentRequest createCommentRequest);
}
