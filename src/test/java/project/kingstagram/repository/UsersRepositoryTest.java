package project.kingstagram.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import project.kingstagram.domain.Users;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class UsersRepositoryTest {
    @Autowired
    UsersRepository usersRepository;
    @Autowired
    FollowRepository followRepository;
    @Test
    public void 사용자정보조회(){
        Users res = usersRepository.findById(11L).orElseThrow();
        System.out.println(res.getUserDescription());
    }
    @Test
    public void 팔로우정보조회(){
        System.out.println(followRepository.findFollowerCountByUserId(1L));
    }

//    @Test
//    void findByUserEmailAndUserPw() {
//        List<Users> users1 = usersRepository.findByUserEmailAndUserPw("dooooh2@naver.com", "Wltnwltn!1212");
//        System.out.println("users1: " + users1.get(0).getUserNickname());
//    }
}