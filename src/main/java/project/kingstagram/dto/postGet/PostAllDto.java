package project.kingstagram.dto.postGet;

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
