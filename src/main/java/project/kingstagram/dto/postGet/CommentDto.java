package project.kingstagram.dto.postGet;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.time.LocalDateTime;

@Getter
public class
CommentDto {
    private Long commentId;
    private String commentContent;
    private LocalDateTime commentTime;
    private Long userId;
    private String userNickname;

    @Builder
    public CommentDto(Long commentId, String commentContent, LocalDateTime commentTime, Long userId, String userNickname) {
        this.commentId = commentId;
        this.commentContent = commentContent;
        this.commentTime = commentTime;
        this.userId = userId;
        this.userNickname = userNickname;
    }

    @Override
    public String toString() {
        return "CommentDto{" +
                "commentId=" + commentId +
                ", commentContent='" + commentContent + '\'' +
                ", commentTime=" + commentTime +
                ", userId=" + userId +
                ", userNickname='" + userNickname + '\'' +
                '}';
    }
}
