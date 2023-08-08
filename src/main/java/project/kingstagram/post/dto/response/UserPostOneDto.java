package project.kingstagram.post.dto.response;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class UserPostOneDto {
    private Long postId;
    private String postContent;
    private String imageUrl;
    private LocalDateTime postTime;
    private Long userId;
    private String userNickname;
    private Long likeCount;
    private Long commentCount;

    public UserPostOneDto(Long postId, String postContent, String imageUrl, LocalDateTime postTime, Long userId, String userNickname, Long likeCount, Long commentCount) {
        this.postId = postId;
        this.postContent = postContent;
        this.imageUrl = imageUrl;
        this.postTime = postTime;
        this.userId = userId;
        this.userNickname = userNickname;
        this.likeCount = likeCount;
        this.commentCount = commentCount;
    }


}
