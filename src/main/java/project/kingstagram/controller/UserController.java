package project.kingstagram.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import project.kingstagram.service.UserService;

import java.lang.reflect.Member;

@Controller
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    // 회원가입 페이지
    @PostMapping("/api/signup")
    public String signup() {
        return "signup";
    }

    // 로그인 페이지
    @PostMapping("api/login")
    public String login() {
        return "login";
    }

    // 로그아웃 페이지
    @PostMapping("api/logout/{uuid}")
    public String logout() {
        return "logout";
    }

}
