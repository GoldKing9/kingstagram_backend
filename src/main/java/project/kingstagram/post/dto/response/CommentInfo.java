package project.kingstagram.post.dto.response;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;

@RequiredArgsConstructor
@Getter
public class CommentInfo {
    private final Long commentId;
    private final String commentContent;
    private final LocalDateTime commentTime;
    private final Long postId;
    private final Long userId;
    private final String userNickname;

}
