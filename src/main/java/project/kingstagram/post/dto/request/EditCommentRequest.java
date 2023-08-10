package project.kingstagram.post.dto.request;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class EditCommentRequest {
    private final Long commentId;
    private final String content;
}
