package project.kingstagram.user.service;

import org.springframework.stereotype.Service;
import project.kingstagram.user.dto.request.UserDTO;
import project.kingstagram.user.dto.response.UserLogInOutDTO;
import project.kingstagram.user.dto.response.UserProfileDTO;
import project.kingstagram.user.dto.response.UserSignUpDTO;

@Service
public interface UserService {

    public void setUserProfile(UserProfileDTO userProfile); // 프로필 편집
    public UserProfileDTO getUserProfile(Long userId); // 프로필 조회

    public UserSignUpDTO setUser(UserDTO user); // 회원가입

    public UserSignUpDTO validateDuplicated(UserDTO user); // 이메일, 닉네임 중복 검증

    public UserLogInOutDTO login(String userEmail, String userPw); // DB에서 이메일, 비번 조회해서 유효한 로그인인지 검증
}
