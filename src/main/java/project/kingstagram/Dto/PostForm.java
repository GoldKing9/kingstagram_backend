package project.kingstagram.Dto;

import lombok.*;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PostForm {
    private String postContent;
    private MultipartFile imageUrl;
}
