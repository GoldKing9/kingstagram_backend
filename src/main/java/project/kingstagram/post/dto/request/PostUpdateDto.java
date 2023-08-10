package project.kingstagram.post.dto.request;

import lombok.*;

import javax.validation.constraints.NotNull;

@Getter @Setter
@NoArgsConstructor
public class PostUpdateDto {
    @NotNull
    private Long postId;
    private String postContent;

    @Builder

    public PostUpdateDto(Long postId, String postContent) {
        this.postId = postId;
        this.postContent = postContent;
    }
}
