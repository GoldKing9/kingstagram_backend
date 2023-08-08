package project.kingstagram.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import project.kingstagram.dto.SetProfileDTO;
import project.kingstagram.dto.UserProfileDTO;
import project.kingstagram.service.SessionService;
import project.kingstagram.service.UserService;

@RestController
@Slf4j
public class UserProfileController {

    private final UserService userService;
    private final SessionService sessionService;

    public UserProfileController(UserService userService, SessionService sessionService) {
        this.userService = userService;
        this.sessionService = sessionService;
    }

    private boolean validateDescription(String description) {
        return description.length() <= 200;
    }

    // 프로필 조회
    @GetMapping("/api/user/profile")
    public UserProfileDTO showUserProfile(@SessionAttribute Long userId) {

        UserProfileDTO output = new UserProfileDTO();

        //uuid가 실제로 세션 해시맵에 존재하는지 판단
        //세션이 없으면 에러라고 코드 -1 리턴
        if(userId == -1){
            output.setResponseCode(-1);
            return output;
        }
//        ---- 이거 다시 짜야 됨!! uuid 안 쓰기로 했는데 관련된 거 다 바꿔야 되겠다..

        // 있으면(세션이 유효하면) 조회 후 리턴
        // uuid에 해당하는 userId를 조회해서 가져옴
        UserProfileDTO res = userService.getUserProfile(userId);
        res.setResponseCode(1);
        return res;
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

    // 프로필 편집 -> 제출
    @PutMapping("/api/user/edit/")
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
