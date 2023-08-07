package project.kingstagram.user.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;
import project.kingstagram.domain.*;
import project.kingstagram.post.dto.PostDto;
import project.kingstagram.repository.FollowRepository;
import project.kingstagram.repository.UsersRepository;

import static org.assertj.core.api.Assertions.assertThat;
@SpringBootTest
@Transactional
class FollowServiceTest {
    @Autowired FollowService followService;
    @Autowired
    FollowRepository followRepository;
    @Autowired
    UsersRepository usersRepository;

    @BeforeEach
    void before(){
        Users fromUser = new Users();
        fromUser.setUserNickname("aaa");
        fromUser.setUserName("경선");
        fromUser.setUserPw("123");
        fromUser.setUserDescription("hello123");
        fromUser.setUserEmail("aaa@gmail.com");
        usersRepository.save(fromUser);

        Users toUser = new Users();
        toUser.setUserNickname("bbb");
        toUser.setUserName("미나");
        toUser.setUserPw("123");
        toUser.setUserDescription("hello");
        toUser.setUserEmail("aaa@gmail.com");
        usersRepository.save(toUser);

    }

    @Rollback(value = false)
    @Test
    void makeFriend(){

        followService.makeFriend(1L,2L);
        Follow follow = followRepository.findById(1L).get();
        assertThat(follow.getFromUser().getUserId()).isEqualTo(2L);


    }

    @Test
    void unFollow(){

    }
    @Test
    void getFollowers(){

    }

}