package project.kingstagram.dto;

import lombok.Data;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import project.kingstagram.domain.Comment;

import java.time.LocalDateTime;

@Getter
@RequiredArgsConstructor
public class CreateCommentRequest {
    private final Long userId;
    private final Long postId;
    private final String content;
    private final LocalDateTime commentTime;

}
