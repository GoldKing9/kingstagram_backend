package project.kingstagram.user.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;
import project.kingstagram.post.service.PostService;
import project.kingstagram.user.dto.response.PostIdAndImageUrlDTO;
import project.kingstagram.user.dto.response.SetProfileDTO;
import project.kingstagram.user.dto.response.UserProfileDTO;
import project.kingstagram.user.dto.UserProfilePostDTO;
import project.kingstagram.user.service.SessionService;
import project.kingstagram.user.service.UserService;

import java.util.List;

@RestController
@Slf4j
public class UserProfileController {
    private final PostService postService;
    private final UserService userService;
    private final SessionService sessionService;

    public UserProfileController(PostService postService, UserService userService, SessionService sessionService) {
        this.postService = postService;
        this.userService = userService;
        this.sessionService = sessionService;
    }

    private boolean validateDescription(String description) {
        return description.length() <= 200;
    }

    // 프로필 조회
    @GetMapping("/api/user/{targetUserId}")
    public UserProfileDTO showUserProfile(@SessionAttribute Long userId, @PathVariable Long targetUserId) {

        UserProfileDTO output = userService.getUserProfile(targetUserId);
        output.setResponseCode(1);
        return output;
//        output.setUserId(res.getUserId());
//        output.setUserName(res.getUserName());
//        output.setUserNickname(res.getUserNickname());
//        output.setUserDescription(res.getUserDescription());
//        output.setPostCount(res.getPostCount());
//        output.setFollowerCount(res.getFollowerCount());
//        output.setFollowingCount(res.getFollowingCount());
//        output.setResponseCode(1);
//        return output;
    }

    // 프로필 게시글 조회
    @GetMapping("/api/user/userProfilePost/{targetUserId}")
    public UserProfilePostDTO showUserProfilePost(@SessionAttribute Long userId, @PathVariable Long targetUserId, Pageable pageable) {

        UserProfilePostDTO output = postService.getMyPost(targetUserId, pageable);

        return output;
    }

    // 프로필 편집 -> 제출
    @PutMapping("/api/user/edit")
    public SetProfileDTO editUserProfile(@SessionAttribute Long userId , @RequestBody SetProfileDTO userDescription) {

        log.info(userDescription.getUserDescription());
        SetProfileDTO output = new SetProfileDTO();

        if (!validateDescription(userDescription.getUserDescription())) {
            output.setResponseCode(-1);
            output.setResponseMessage("소개글은 200자를 넘을 수 없습니다.");
            return output;
        }

        // 세션이 없으면 에러라고 코드 -1 리턴
        if(userId == -1) {
            output.setResponseCode(-1);
            output.setResponseMessage("세션이 만료되었습니다.");
            return output;
        }

        // 있으면(세션이 유효하면) 해당 유저 엔티티 내용 수정 후 리턴
        UserProfileDTO input = new UserProfileDTO();
        input.setUserId(userId);
        input.setUserDescription(userDescription.getUserDescription());
        userService.setUserProfile(input);

        output.setResponseCode(1);
        output.setResponseMessage("프로필 편집이 완료되었습니다.");
        return output;
    }


}
