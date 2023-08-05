package project.kingstagram.service;

import org.springframework.stereotype.Service;
import project.kingstagram.Dto.UserDTO;
import project.kingstagram.Dto.UserProfileDTO;

@Service
public interface UserService {

    public void setUserProfile(UserProfileDTO userProfile); // 프로필 편집
    public UserProfileDTO getUserProfile(Long userId); // 프로필 조회

    public void setUser(UserDTO user); // 회원가입

}