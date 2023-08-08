package project.kingstagram.user.dto.response;

import lombok.Data;

@Data
public class SetProfileDTO {
    String userDescription;
    int responseCode;
    String responseMessage;
}
