package project.kingstagram.user.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import project.kingstagram.user.dto.request.UserDTO;
import project.kingstagram.user.dto.response.UserLogInOutDTO;
import project.kingstagram.user.dto.response.UserSignUpDTO;
import project.kingstagram.user.service.UserService;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@RestController
@Slf4j
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    private boolean validateEmail(String email) {
        String emailPattern = "^[A-Za-z0-9]([-_.]?[A-Za-z0-9])*@[A-Za-z0-9]([-_.]?[A-Za-z0-9])*\\.[A-Za-z]{2,3}$";
        return email.matches(emailPattern);
    }
    private boolean validateNickname(String nickname) {
        String nicknamePattern = "^(?=.*[a-z])(?=.*[_0-9]?)(?=.{4,16}$).*";
        return !nickname.contains(" ") && nickname.matches(nicknamePattern);
    }
    private boolean validateName(String name) {
        String namePattern = "^(?!(?:[ㄱ-ㅎㅏ-ㅣ]+|[aeiouAEIOU]+)$)[가-힣]{2,5}$";
        return !name.contains(" ") && name.matches(namePattern);
    }
    private boolean validatePw(String pw) {
        String passwordPattern = "^(?=.*[A-Z])(?=.*[0-9])(?=.*[a-z])(?=.*[!@#$%^&*\\[\\]{}<>,.\\\\/_=+()]).{8,16}$";
        return !pw.contains(" ") && pw.matches(passwordPattern);
    }

    // 회원가입
    @PostMapping("/api/signup")
    public UserSignUpDTO signup(@RequestBody UserDTO user) {

        // 이메일, 비번 중복 검증
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
    @PostMapping("/api/login")
    public UserLogInOutDTO login(HttpServletRequest httpServletRequest, @RequestBody UserDTO user, HttpServletResponse response) {

        HttpSession session = httpServletRequest.getSession(false);
        log.info(user.getUserEmail());
        log.info(user.getUserPw());

        UserLogInOutDTO output = new UserLogInOutDTO();

        if(session != null){ //이미 세션이 존재하는 사용자
            output.setResponseMessage("이미 로그인 되어있습니다");
            output.setResponseCode(-1);
            return output;
        }

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

        UserLogInOutDTO res = userService.login(user.getUserEmail(),user.getUserPw());

        // 로그인 실패
        if(res.getResponseCode() == -1) {
            output.setResponseCode(-1);
            output.setResponseMessage("이메일 또는 비번이 잘못됨");
            return output;
        }

        // 로그인 성공 (DB에서 아이디 비번 조회 후 세션 생성)
        HttpSession httpSession =  httpServletRequest.getSession();
        httpSession.setAttribute("userId" , res.getUserId());
        log.info(httpSession.getId());
        Cookie cookie  = new Cookie("LOGINCHECK", "ok");
        cookie.setMaxAge(-1);
        response.addCookie(cookie);
        return res;
    }

    // 로그아웃
    @PostMapping("/api/logout")
    public UserLogInOutDTO logout(HttpServletRequest httpServletRequest,  HttpServletResponse response) {

        UserLogInOutDTO output = new UserLogInOutDTO();
        HttpSession httpSession =  httpServletRequest.getSession(false); //세션이 있으면 기존 세션 반환, 없으면 null반환
        if(httpSession != null){
            log.info("========/api/logout====== ");
            httpSession.invalidate(); //만료
            Cookie cookie = new Cookie("JSESSIONID", null);
            Cookie check_login = new Cookie("LOGINCHECK", null);
            cookie.setMaxAge(0);
            response.addCookie(cookie);

            check_login.setMaxAge(0);
            response.addCookie(check_login);
            log.info("cookie = {}", cookie.getName());
            log.info("LOGINCHECK = {}", check_login.getName());


        }

        output.setResponseCode(1);
        output.setResponseMessage("logout ok");

        return output;
    }





}
