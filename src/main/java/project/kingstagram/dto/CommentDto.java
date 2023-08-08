package project.kingstagram.dto;

import lombok.*;
import project.kingstagram.domain.Comment;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CommentDto {
    private String commentContent;

    public Comment toEntity(){
        return Comment.builder()
                .commentContent(this.commentContent)
                .build();
    }

}
