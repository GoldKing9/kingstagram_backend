package project.kingstagram.Dto;

import lombok.Data;

@Data
public class UserLogInOutDTO {
    private int responseCode;
    private String responseMessage;
    private String uuid;
}
