package project.kingstagram.user.dto.response;

import lombok.Data;

@Data
public class UserProfileDTO {
    private Long userId;
    private String userName;
    private String userNickname;
    private String userDescription;
    private Long postCount;
    private Long followerCount;
    private Long followingCount;
    private int responseCode;
    private String responseMessage;
}
