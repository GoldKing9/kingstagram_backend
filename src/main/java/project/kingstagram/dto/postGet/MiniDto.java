package project.kingstagram.dto.postGet;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

@Getter @Setter
@ToString

public class MiniDto {

    private Long postId;
    private String postContent;
    private String imageUrl;
    private LocalDateTime postTime;
    private Long userId;
    private String userNickname;
    private Long likeCount;

    @Builder
    public MiniDto(Long postId, String postContent, String imageUrl, LocalDateTime postTime, Long userId, String userNickname, Long likeCount) {
        this.postId = postId;
        this.postContent = postContent;
        this.imageUrl = imageUrl;
        this.postTime = postTime;
        this.userId = userId;
        this.userNickname = userNickname;
        this.likeCount = likeCount;
    }
}
