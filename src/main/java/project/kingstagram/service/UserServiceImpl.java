package project.kingstagram.service;

import org.springframework.stereotype.Service;
import project.kingstagram.dto.UserDTO;
import project.kingstagram.dto.UserProfileDTO;
import project.kingstagram.dto.UserSignUpDTO;
import project.kingstagram.domain.Users;
import project.kingstagram.repository.FollowRepository;
import project.kingstagram.repository.PostRepository;
import project.kingstagram.repository.UsersRepository;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private final UsersRepository usersRepository;
    private final PostRepository postRepository;
    private final FollowRepository followRepository;

    public UserServiceImpl(UsersRepository usersRepository, PostRepository postRepository, FollowRepository followRepository) {
        this.usersRepository = usersRepository;
        this.postRepository = postRepository;
        this.followRepository = followRepository;
    }

    @Override
    public void setUserProfile(UserProfileDTO userProfile) { // 프로필 편집
        Users userInfo = usersRepository.findById(userProfile.getUserId())
                .orElseThrow(() -> new EntityNotFoundException("entity not found"));

        userInfo.setUserDescription(userProfile.getUserDescription());

        usersRepository.save(userInfo);
    }

    @Override
    public UserProfileDTO getUserProfile(Long userId) { // 프로필 조회
        Users userInfo = usersRepository.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException("entity not found"));
        Integer postCountByUserId = postRepository.findPostCountByUserId(userId);
        Integer followingCountByUserId = followRepository.findFollowingCountByUserId(userId);
        Integer followerCountByUserId = followRepository.findFollowerCountByUserId(userId);


        UserProfileDTO output = new UserProfileDTO();
        output.setUserId(userInfo.getUserId());
        output.setUserName(userInfo.getUserName());
        output.setUserNickname(userInfo.getUserNickname());
        output.setUserDescription(userInfo.getUserDescription());
        output.setPostCount(postCountByUserId);
        output.setFollowingCount(followingCountByUserId);
        output.setFollowerCount(followerCountByUserId);

        return output;

    }

    @Override
    public UserSignUpDTO setUser(UserDTO user) { // 회원가입 정보 DB에 저장
        Users userInfo = new Users();
        UserSignUpDTO output = new UserSignUpDTO();

        userInfo.setUserEmail(user.getUserEmail());
        userInfo.setUserNickname(user.getUserNickname());
        userInfo.setUserName(user.getUserName());
        userInfo.setUserPw(user.getUserPw());

        try {
            usersRepository.save(userInfo);
        } catch (Exception e) {
            output.setResponseCode(-1);
            return output;
        }

        output.setResponseCode(1);
        return output;
    }

    @Override
    public Long login(String userEmail, String userPw) { // DB에서 이메일, 비번 조회해서 유효한 로그인인지 검증
        List<Users> validateUserEmailAndUserPw = usersRepository.findByUserEmailAndUserPw(userEmail, userPw);

        if(validateUserEmailAndUserPw.size() == 0) {
            return -1L;
        }
        return validateUserEmailAndUserPw.get(0).getUserId();
    }
}
