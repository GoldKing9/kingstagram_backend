package project.kingstagram.user.dto.response;

import lombok.Getter;
import lombok.Setter;

@Getter
public class FromUserDto {

    private Long userId;
    private String userName;
    private String userNickname;

    public FromUserDto(Long userId, String userName, String userNickname) {
        this.userId = userId;
        this.userName = userName;
        this.userNickname = userNickname;
    }
}
