package project.kingstagram.repository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;
import project.kingstagram.domain.Users;
import project.kingstagram.user.dto.response.ToUserDto;
import project.kingstagram.user.service.FollowService;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
@Transactional
@Rollback(value = false)
class FollowRepositoryTest {

    @Autowired FollowRepository followRepository;
    @Autowired
    UsersRepository usersRepository;
    @Autowired
    FollowService followService;

    @BeforeEach
    void before(){

        Users toUser = new Users();
        toUser.setUserNickname("bbb");
        toUser.setUserName("미나");
        toUser.setUserPw("123");
        toUser.setUserDescription("hello");
        toUser.setUserEmail("aaa@gmail.com");
        usersRepository.save(toUser);

        for(int i=1;i<=10;i++){
            Users fromUser = new Users();
            fromUser.setUserNickname("aaa");
            fromUser.setUserName("경선");
            fromUser.setUserPw("123");
            fromUser.setUserDescription("hello123");
            fromUser.setUserEmail("aaa@gmail.com");
            Users  saveUser = usersRepository.save(fromUser);
            Long fromUserId = saveUser.getUserId();
            followService.makeFriend(1L, fromUserId);
        }

        followService.makeFriend(3L,2L);
        followService.makeFriend(4L,2L);

    }
    @Test
    void fromUsers(){
        followRepository.findFromUserAllByUserId(1L);
    }

    @Test
    void toUsers(){
        List<ToUserDto> toUserAllByUserId = followRepository.findToUserAllByUserId(2L);
        for (ToUserDto toUserDto : toUserAllByUserId) {
            System.out.println("내가 팔로우 하는 사람 : "+toUserDto.getUserId());

        }

    }
    @Test
    void deleteToUser(){
        followRepository.deleteToUserByUserId(1L, 2L);
    }

}