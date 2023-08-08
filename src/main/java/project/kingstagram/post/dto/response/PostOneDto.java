package project.kingstagram.post.dto.response;

import lombok.*;

import java.time.LocalDateTime;
import java.util.List;
@Getter
@Setter
@ToString
@NoArgsConstructor
public class PostOneDto {
     private Long postId;
     private String postContent;
     private String imageUrl;
     private LocalDateTime postTime;
     private Long userId;
     private String userNickname;
     private Long likeCount;


    @Builder
    public PostOneDto(Long postId, String postContent, String imageUrl, LocalDateTime postTime, Long userId, String userNickname, Long likeCount) {
        this.postId = postId;
        this.postContent = postContent;
        this.imageUrl = imageUrl;
        this.postTime = postTime;
        this.userId = userId;
        this.userNickname = userNickname;
        this.likeCount = likeCount;
    }
}
