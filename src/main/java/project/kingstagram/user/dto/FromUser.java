package project.kingstagram.user.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FromUser {
    private Long userId;
    private String userName;
    private String userNickname;
}
