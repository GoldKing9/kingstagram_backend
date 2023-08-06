package project.kingstagram.dto;

import lombok.Data;

@Data
public class UserDTO {
    private Long userId;
    private String userName;
    private String userNickname;
    private String userPw;
    private String userEmail;
}
