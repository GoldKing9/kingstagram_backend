package project.kingstagram.user.dto.response;

import lombok.Getter;

@Getter
public class ToUserDto {
    private Long userId;
    private String userName;
    private String userNickname;

    public ToUserDto(Long userId, String userName, String userNickname) {
        this.userId = userId;
        this.userName = userName;
        this.userNickname = userNickname;
    }
}
