package project.kingstagram.user.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
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
        Users fromUser = Users.builder()
                .userDescription("hello123")
                .userPw("123")
                .userEmail("aaa@gmail.com")
                .userName("경선")
                .userNickname("aaa")
                .build();
        usersRepository.save(fromUser);

        Users toUser = Users.builder()
                .userDescription("hello")
                .userPw("123")
                .userEmail("aaa@gmail.com")
                .userName("미나")
                .userNickname("bbb")
                .build();
        usersRepository.save(toUser);

    }
    @Test
    @Rollback(value = false)
    @DisplayName("userId가 toUserId를 팔로우 신청")
    void makeFriend(){

        followService.makeFriend(1L,2L);
        Follow follow = followRepository.findById(1L).get();
        assertThat(follow.getFromUser().getUserId()).isEqualTo(2L);


    }



}