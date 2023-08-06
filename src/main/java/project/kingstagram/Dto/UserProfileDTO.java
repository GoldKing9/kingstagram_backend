package project.kingstagram.Dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
public class UserProfileDTO {
    private Long userId;
    private String userName;
    private String userNickname;
    private String userDescription;
    private int postCount;
    private int followerCount;
    private int followingCount;
    private int responseCode;
    private String responseMessage;
}
