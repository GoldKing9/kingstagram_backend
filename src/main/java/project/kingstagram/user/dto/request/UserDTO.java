package project.kingstagram.user.dto.request;

import lombok.Data;

@Data
public class UserDTO {
    private Long userId;
    private String userName;
    private String userNickname;
    private String userPw;
    private String userEmail;
}
