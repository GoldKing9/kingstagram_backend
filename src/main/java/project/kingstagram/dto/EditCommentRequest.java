package project.kingstagram.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;

@Getter
@RequiredArgsConstructor
public class EditCommentRequest {
    private final Long userId;
    private final Long commentId;
    private final String content;
}
