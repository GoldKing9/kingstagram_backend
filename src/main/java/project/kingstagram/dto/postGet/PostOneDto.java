package project.kingstagram.dto.postGet;

import lombok.*;
import project.kingstagram.dto.postGet.CommentDto;

import java.time.LocalDateTime;
import java.util.ArrayList;
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
     private List<CommentDto> comments;

    @Builder
    public PostOneDto(Long postId, String postContent, String imageUrl, LocalDateTime postTime, Long userId, String userNickname, Long likeCount, List<CommentDto> comments) {
        this.postId = postId;
        this.postContent = postContent;
        this.imageUrl = imageUrl;
        this.postTime = postTime;
        this.userId = userId;
        this.userNickname = userNickname;
        this.likeCount = likeCount;
        this.comments = comments;
    }
}
