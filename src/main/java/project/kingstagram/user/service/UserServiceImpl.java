package project.kingstagram.user.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import project.kingstagram.user.dto.request.UserDTO;
import project.kingstagram.user.dto.response.UserLogInOutDTO;
import project.kingstagram.user.dto.response.UserProfileDTO;
import project.kingstagram.user.dto.response.UserSignUpDTO;
import project.kingstagram.domain.Users;
import project.kingstagram.repository.FollowRepository;
import project.kingstagram.repository.PostRepository;
import project.kingstagram.repository.UsersRepository;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
@Slf4j
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
        output.setResponseMessage("ok");
        return output;
    }

    // 회원가입 시 이메일, 닉네임 중복 검증
    @Override
    public UserSignUpDTO validateDuplicated(UserDTO user) {

        UserSignUpDTO output = new UserSignUpDTO();

        try {
            if (usersRepository.findByUserEmail(user.getUserEmail()).isPresent()) {
                throw new IllegalStateException("이미 존재하는 이메일입니다.");
            }
            if (usersRepository.findByUserNickname(user.getUserNickname()).isPresent()) {
                throw new IllegalStateException("이미 존재하는 닉네임입니다.");
            }
        } catch (IllegalStateException e) {
            output.setResponseCode(-1);
            output.setResponseMessage(e.getMessage());
            return output;
        }
       return output;
    }

    // DB에서 이메일, 비번 조회해서 유효한 로그인인지 검증
    @Override
    public UserLogInOutDTO login(String userEmail, String userPw) {
        List<Users> validateUserEmailAndUserPw = usersRepository.findByUserEmailAndUserPw(userEmail, userPw);
        UserLogInOutDTO output = new UserLogInOutDTO();

        log.info("userEmail={}", userEmail);
        log.info("userPw={}", userPw);

        // 로그인 실패
        if (validateUserEmailAndUserPw.size() == 0) {
            output.setResponseCode(-1);
            output.setResponseMessage("로그인 실패");
            return output;
        }

        // 로그인 성공
        Users res = validateUserEmailAndUserPw.get(0);
        output.setUserId(res.getUserId());
        output.setUserName(res.getUserName());
        output.setUserNickname(res.getUserNickname());
        output.setResponseCode(1);
        output.setResponseMessage("로그인 성공");
        return output;
    }
    // 왜 List를 사용하지? => 방어 로직! 혹시 이메일이 중복으로 들어가는 경우가 있으면 count가 2인 리스트로는 받을 수 있는데 그냥 Users 객체로 받으려고 하면 에러 남
    // 에러 처리를 다 해 줄 거면 상관 없는데 안 할 거니까 list로 구현하는 게 나을 듯 해서 List 사용
    // if (Users == null) 로 체크하면 안 되나? => List를 안 썼으면 가능
}
