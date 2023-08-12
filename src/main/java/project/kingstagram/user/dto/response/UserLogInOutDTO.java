package project.kingstagram.user.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
public class UserLogInOutDTO {

    private Long userId;
    private String userName;
    private String userNickname;
    private int responseCode;
    private String responseMessage;

}
