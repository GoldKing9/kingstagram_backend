package project.kingstagram.post.dto;

import lombok.*;
import project.kingstagram.domain.Post;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PostDto {
    private String postContent;
    private String imageUrl;


    //생성함수?
    public Post toEntity(){
        return Post.builder()
                .postContent(this.postContent)
                .imageUrl(this.imageUrl)
                .build();
    }

}
