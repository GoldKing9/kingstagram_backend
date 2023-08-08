package project.kingstagram.dto;

import lombok.Data;

@Data
public class SetProfileDTO {
    String userDescription;
    int responseCode;
    String responseMessage;
}
