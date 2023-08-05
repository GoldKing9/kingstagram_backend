package project.kingstagram.service;

import org.springframework.stereotype.Service;
import project.kingstagram.Dto.UserDTO;
import project.kingstagram.Dto.UserProfileDTO;
import project.kingstagram.Dto.UserSignUpDTO;
import project.kingstagram.repository.PostRepository;
import project.kingstagram.repository.UsersRepository;

@Service
public class UserServiceImpl implements UserService {

    private final UsersRepository usersRepository;
    private final PostRepository postRepository;

    public UserServiceImpl(UsersRepository usersRepository, PostRepository postRepository) {
        this.usersRepository = usersRepository;
        this.postRepository = postRepository;
    }

    @Override
    public void setUserProfile(UserProfileDTO userProfile) { // 프로필 편집

    }

    @Override
    public UserProfileDTO getUserProfile(Long userId) { // 프로필 조회
        return null;
    }

    @Override
    public UserSignUpDTO setUser(UserDTO user) { // 회원가입
        return null;
    }

    @Override
    public Long login(String userEmail, String userPw) { // DB에서 이메일, 비번 조회해서 유효한 로그인인지 검증
        return null;
    }
}
