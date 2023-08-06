package project.kingstagram.dto;

import lombok.Data;

@Data
public class UserLogInOutDTO {
    private int responseCode;
    private String responseMessage;
    private String uuid;
}
