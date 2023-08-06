package project.kingstagram.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import project.kingstagram.dto.UserProfileDTO;
import project.kingstagram.service.SessionService;
import project.kingstagram.service.UserService;

@Controller
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
    @GetMapping("/api/user/{uuid}")
    public UserProfileDTO showUserProfile(@PathVariable String uuid) {

        UserProfileDTO output = new UserProfileDTO();
        Long userId = sessionService.getUserId(uuid);

        //uuid가 실제로 세션 해시맵에 존재하는지 판단
        //세션이 없으면 에러라고 코드 -1 리턴
        if(userId == -1){
            output.setResponseCode(-1);
            return output;
        }

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
    @PutMapping("/api/user/edit/{uuid}")
    public UserProfileDTO editUserProfile(@PathVariable String uuid, @RequestBody String userDescription) {

        UserProfileDTO output = new UserProfileDTO();
        Long userId = sessionService.getUserId(uuid);

        if (!validateDescription(userDescription)) {
            output.setResponseCode(-1);
            output.setResponseMessage("소개글은 200자를 넘을 수 없습니다.");
            return output;
        }

        // uuid가 실제로 세션 해시맵에 존재하는지 판단
        // 세션이 없으면 에러라고 코드 -1 리턴
        if(userId == -1) {
            output.setResponseCode(-1);
            output.setResponseMessage("세션이 만료되었습니다.");
            return output;
        }

        // 있으면(세션이 유효하면) 조회 후 리턴
        // uuid에 해당하는 userId를 조회해서 가져옴
        UserProfileDTO input = new UserProfileDTO();
        input.setUserId(userId);
        input.setUserDescription(userDescription);
        userService.setUserProfile(input);

        output.setResponseCode(1);
        return output;
    }


}
