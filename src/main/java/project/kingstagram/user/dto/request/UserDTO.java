package project.kingstagram.user.dto.request;

import lombok.Data;
import org.springframework.validation.annotation.Validated;


@Data
public class UserDTO {
    private Long userId;
    private String userName;
    private String userNickname;
    private String userPw;
    private String userEmail;
}
