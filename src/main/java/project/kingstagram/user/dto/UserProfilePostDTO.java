package project.kingstagram.user.dto;

import lombok.Data;
import project.kingstagram.user.dto.response.PostIdAndImageUrlDTO;

import java.util.List;

@Data
public class UserProfilePostDTO {
    int totalPages;
    List<PostIdAndImageUrlDTO> postIdAndImageUrlDTOList;
}
