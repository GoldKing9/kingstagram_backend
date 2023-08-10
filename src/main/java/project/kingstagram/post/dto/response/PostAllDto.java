package project.kingstagram.post.dto.response;

import lombok.Builder;
import lombok.Getter;

@Getter
public class
PostAllDto {
    private PostOneDto post;


    @Builder
    public PostAllDto(PostOneDto post) {
        this.post = post;
    }
}
