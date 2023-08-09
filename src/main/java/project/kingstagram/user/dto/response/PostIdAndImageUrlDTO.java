package project.kingstagram.user.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@ToString
public class PostIdAndImageUrlDTO{
    Long postId;
    String imageUrl;

    public PostIdAndImageUrlDTO(Long postId, String imageUrl) {
        this.postId = postId;
        this.imageUrl = imageUrl;
    }
    // 왜 생성자 명시적으로 안 써 놓으면 JPA 쿼리에 문제가 생기지?

}
