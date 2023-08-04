package project.kingstagram.Dto;

import lombok.*;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PostCreateForm {
    private String postContent;
    private MultipartFile imageUrl;
}
