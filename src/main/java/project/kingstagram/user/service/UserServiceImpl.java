package project.kingstagram.user.service;

import org.springframework.stereotype.Service;
import project.kingstagram.user.dto.request.UserDTO;
import project.kingstagram.user.dto.response.UserProfileDTO;
import project.kingstagram.user.dto.response.UserSignUpDTO;
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

    // 프로필 편집
    @Override
    public void setUserProfile(UserProfileDTO userProfile) {
        Users userInfo = usersRepository.findById(userProfile.getUserId())
                .orElseThrow(() -> new EntityNotFoundException("entity not found"));

        userInfo.setUserDescription(userProfile.getUserDescription());

        usersRepository.save(userInfo);
    }

    // 프로필 조회
    @Override
    public UserProfileDTO getUserProfile(Long userId) {
        Users userInfo = usersRepository.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException("entity not found"));

        Long postCountByUserId = postRepository.findPostCountByUserId(userId);
        Long followingCountByUserId = followRepository.findFollowingCountByUserId(userId);
        Long followerCountByUserId = followRepository.findFollowerCountByUserId(userId);


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

    // 회원가입 정보 DB에 저장
    @Override
    public UserSignUpDTO setUser(UserDTO user) {
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

    // DB에서 이메일, 비번 조회해서 유효한 로그인인지 검증
    @Override
    public Long login(String userEmail, String userPw) {
        List<Users> validateUserEmailAndUserPw = usersRepository.findByUserEmailAndUserPw(userEmail, userPw);

        if(validateUserEmailAndUserPw.size() == 0) {
            return -1L;
        }
        return validateUserEmailAndUserPw.get(0).getUserId();
    }
    // 왜 List를 사용하지? => 방어 로직! 혹시 이메일이 중복으로 들어가는 경우가 있으면 count가 2인 리스트로는 받을 수 있는데 그냥 Users 객체로 받으려고 하면 에러 남
    // 에러 처리를 다 해 줄 거면 상관 없는데 안 할 거니까 list로 구현하는 게 나을 듯 해서 List 사용
    // if (Users == null) 로 체크하면 안 되나? => List를 안 썼으면 가능
}
