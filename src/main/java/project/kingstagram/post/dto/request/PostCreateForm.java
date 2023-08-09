package project.kingstagram.post.dto.request;

import lombok.*;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
@NoArgsConstructor
public class PostCreateForm {
    private String postContent;
    private MultipartFile imageUrl;

    @Builder
    public PostCreateForm(String postContent, MultipartFile imageUrl) {
        this.postContent = postContent;
        this.imageUrl = imageUrl;
    }
}
