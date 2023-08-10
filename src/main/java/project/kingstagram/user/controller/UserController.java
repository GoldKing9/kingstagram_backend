package project.kingstagram.user.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import project.kingstagram.user.dto.request.UserDTO;
import project.kingstagram.user.dto.response.UserLogInOutDTO;
import project.kingstagram.user.dto.response.UserSignUpDTO;
import project.kingstagram.user.service.SessionService;
import project.kingstagram.user.service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@RestController
@Slf4j
public class UserController {

    private final UserService userService;
    private final SessionService sessionService;

    public UserController(UserService userService, SessionService sessionService) {
        this.userService = userService;
        this.sessionService = sessionService;
    }

    private boolean validateEmail(String email) {
        String emailPattern = "^[a-zA-Z0-9!@#$%^&*]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,3}$";
        return email.matches(emailPattern);
    }
    private boolean validateNickname(String nickname) {
        String nicknamePattern = "^[a-z0-9_]+$";
        return !nickname.contains(" ") && nickname.matches(nicknamePattern);
    }
    private boolean validateName(String name) {
        return !name.contains(" ") && name.length() <= 16;
    }
    private boolean validatePw(String pw) {
        String passwordPattern = "^(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#$%^&*]).{8,16}$";
        return !pw.contains(" ") && pw.matches(passwordPattern);
    }

    // 회원가입
    @PostMapping("/api/signup")
    public UserSignUpDTO signup(@RequestBody UserDTO user) {

        UserSignUpDTO validateDupl = userService.validateDuplicated(user);
        if (validateDupl.getResponseCode() == -1 || validateDupl.getResponseMessage() != null) {
            return validateDupl;
        }

        UserSignUpDTO output = new UserSignUpDTO();

        if(!validateEmail(user.getUserEmail())){
            output.setResponseCode(-1);
            output.setResponseMessage("이메일 형식에 맞게 작성해 주세요");
            return output;
        }

        if(!validateNickname(user.getUserNickname())){
            output.setResponseCode(-1);
            output.setResponseMessage("사용자 이름(Nickname)은 띄어쓰기 없이, 영어소문자,숫자로만 작성해주세요");
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

    // 세션 테스트
    @GetMapping("/api/test")
    public Integer test(@SessionAttribute int userId){
        return userId;
    }

    // 로그인
    @PostMapping("api/login")
    public UserLogInOutDTO login(HttpServletRequest httpServletRequest, @RequestBody UserDTO user) {
        String jSessionId = httpServletRequest.getRequestedSessionId();
        log.info(jSessionId);
        log.info(user.getUserEmail());
        log.info(user.getUserPw());
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

        // 로그인 실패
        Long userId = userService.login(user.getUserEmail(),user.getUserPw());
        if(userId == -1) {
            output.setResponseCode(-1);
            output.setResponseMessage("아이디 또는 비번이 잘못됨");
            return output;
        }

        // DB에서 아이디 비번 조회 후 세션 생성
        HttpSession httpSession =  httpServletRequest.getSession();
        httpSession.setAttribute("userId" , userId);
        log.info(httpSession.getId());

        output.setResponseCode(1);
        output.setResponseMessage("로그인 성공");

        return output;
    }

    // 로그아웃
    @PostMapping("api/logout")
    public UserLogInOutDTO logout(HttpServletRequest httpServletRequest) {

        UserLogInOutDTO output = new UserLogInOutDTO();
        HttpSession httpSession =  httpServletRequest.getSession();
        httpSession.removeAttribute("userId"); // removeAttribute() 메서드 쓰려고 @SessionAttribute 말고
        output.setResponseCode(1);
        return output;
    }


}
