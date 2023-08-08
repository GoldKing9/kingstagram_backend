package project.kingstagram.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class DeleteCommentRequest {
    private final Long CommentId;
}
