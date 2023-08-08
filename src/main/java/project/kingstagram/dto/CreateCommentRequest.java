package project.kingstagram.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;

@Getter
@RequiredArgsConstructor
public class CreateCommentRequest {
    private final Long postId;
    private final String content;
    private final LocalDateTime commentTime;

}
