package project.kingstagram.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import project.kingstagram.Dto.UserDTO;
import project.kingstagram.Dto.UserLogInOutDTO;
import project.kingstagram.Dto.UserSignUpDTO;
import project.kingstagram.service.SessionService;
import project.kingstagram.service.UserService;

@Controller
public class UserController {

    private final UserService userService;
    private final SessionService sessionService;

    public UserController(UserService userService, SessionService sessionService) {
        this.userService = userService;
        this.sessionService = sessionService;
    }

    private boolean validateEmail(String email) {
        return true;
    }
    private boolean validateNickname(String nickname) {
        return true;
    }
    private boolean validateName(String name) {
        return true;
    }
    private boolean validatePw(String pw) {
        return true;
    }

    // 회원가입
    @PostMapping("/api/signup")
    public UserSignUpDTO signup(@RequestBody UserDTO user) {

        UserSignUpDTO output = new UserSignUpDTO();

        if(!validateEmail(user.getUserEmail())){
            output.setResponseCode(-1);
            output.setResponseMessage("이메일 형식에 맞게 작성해 주세요");
            return output;
        }

        if(!validateNickname(user.getUserNickname())){
            output.setResponseCode(-1);
            output.setResponseMessage("사용자 이름(Nickname)은 띄어쓰기 없이, 영어소문자,숫자,_로만 작성해주세요");
            return output;
        }

        if(!validateName(user.getUserName())){
            output.setResponseCode(-1);
            output.setResponseMessage("성명은 16자 이하로, 띄어쓰기 없이, 숫자나 특수기호를 사용하지 않고 작성해주세요");
            return output;
        }

        if(!validatePw(user.getUserPw())){
            output.setResponseCode(-1);
            output.setResponseMessage("비밀번호는 8자 이상 16자 이하로, 띄어쓰기 없이, 영어대문자, 소문자, 특수기호가 포함되도록 작성해주세요");
            return output;
        }

        // 회원가입 성공한 유저 정보 셋팅
        UserDTO input = new UserDTO();

        input.setUserEmail(user.getUserEmail());
        input.setUserNickname(user.getUserNickname());
        input.setUserName(user.getUserName());
        input.setUserPw(user.getUserPw());

        // 회원가입 성공한 유저 정보 저장
        UserSignUpDTO res = userService.setUser(input);
        return res;

    }

    // 로그인
    @PostMapping("api/login")
    public UserLogInOutDTO login(@RequestBody UserDTO user) {

        UserLogInOutDTO output = new UserLogInOutDTO();

        if(!validateEmail(user.getUserEmail())){
            output.setResponseCode(-1);
            output.setResponseMessage("이메일 형식에 맞게 작성해 주세요");
            return output;
        }

        if(!validatePw(user.getUserPw())){
            output.setResponseCode(-1);
            output.setResponseMessage("비밀번호는 8자 이상 16자 이하로, 띄어쓰기 없이, 영어대문자, 소문자, 특수기호가 포함되도록 작성해주세요");
            return output;
        }

        // 로그인 성공한 유저 uuid 생성하고 딕셔너리에 저장 후 uuid와 1 리턴, 로그인 실패하면 -1 리턴
        String uuid = null;
        try {
            uuid = sessionService.InsertUuid(user.getUserId());
            if(uuid == null) throw new RuntimeException("로그인 실패");
        } catch(Exception e){//로그인 실패한 경우
            output.setResponseCode(-1);
            output.setResponseMessage("로그인 실패");
            return output;
        }
        output.setResponseCode(1);
        output.setResponseMessage("로그인 성공");
        output.setUuid(uuid);
        return output;
    }

    // 로그아웃
    @PostMapping("api/logout/{uuid}")
    public UserLogInOutDTO logout(@PathVariable String uuid) {

        UserLogInOutDTO output = new UserLogInOutDTO();
        Long res = sessionService.deleteUuid(uuid);
        output.setResponseCode(1);
        return output;
    }

}
