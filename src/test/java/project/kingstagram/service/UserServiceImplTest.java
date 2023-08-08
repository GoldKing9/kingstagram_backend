package project.kingstagram.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import project.kingstagram.user.dto.response.UserDTO;
import project.kingstagram.user.dto.response.UserProfileDTO;
import project.kingstagram.repository.FollowRepository;
import project.kingstagram.repository.PostRepository;
import project.kingstagram.repository.UsersRepository;
import project.kingstagram.user.service.UserServiceImpl;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest
class UserServiceImplTest {

    @Autowired
    UserServiceImpl userService;
    @Autowired UsersRepository usersRepository;
    @Autowired PostRepository postRepository;
    @Autowired  FollowRepository followRepository;

    @Test
    void setUserProfile() {
        UserProfileDTO userProfileDTO = new UserProfileDTO();
        userProfileDTO.setUserId(11L);
        userProfileDTO.setUserDescription("안녕하세요");
        userService.setUserProfile(userProfileDTO);
    }

    @Test
    void getUserProfile() {
        UserProfileDTO userProfileDTO = userService.getUserProfile(21L);
        System.out.println(userProfileDTO.toString());
        assertThat(userProfileDTO.getUserDescription()).isEqualTo("안녕하세요");
    }

    @Test
    void setUser() {
        UserDTO userDTO = new UserDTO();
        userDTO.setUserEmail("dooooh2@naver.com");
        userDTO.setUserNickname("Jisoo");
        userDTO.setUserName("김지수");
        userDTO.setUserPw("Rlawltn123");
        userService.setUser(userDTO);
    }

    @Test
    void login_성공() {
        Long Jisoo = userService.login("dooooh2@naver.com", "Rlawltn123");
        assertThat(Jisoo).isEqualTo(125L);
    }
    @Test
    void login_실패() {
        Long Jisoo = userService.login("dooooh2@naver.com", "rlawltn123");
        assertThat(Jisoo).isNotEqualTo(125L);
    }
}