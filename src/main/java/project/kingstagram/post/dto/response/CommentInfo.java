package project.kingstagram.post.dto.response;

import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;

@RequiredArgsConstructor
public class CommentInfo {
    private final Long commentId;
    private final String commentContent;
    private final LocalDateTime commentTime;
    private final Long postId;
    private final Long userId;
    private final String userNickname;

}
