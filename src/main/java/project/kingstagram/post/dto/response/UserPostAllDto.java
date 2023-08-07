package project.kingstagram.post.dto.response;

import lombok.Builder;
import lombok.Getter;

import java.util.List;
@Getter
public class UserPostAllDto {
    private List<UserPostOneDto> posts;
    @Builder
    public UserPostAllDto(List<UserPostOneDto> posts) {
        this.posts = posts;
    }
}
