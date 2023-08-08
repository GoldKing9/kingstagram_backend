package project.kingstagram.user.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
public class UserLogInOutDTO {
    private int responseCode;
    private String responseMessage;

}
