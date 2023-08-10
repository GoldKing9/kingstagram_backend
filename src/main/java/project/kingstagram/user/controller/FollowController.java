package project.kingstagram.user.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import project.kingstagram.user.dto.response.FromUserAllDto;
import project.kingstagram.user.dto.response.ToUserAllDto;
import project.kingstagram.user.service.FollowService;
@Slf4j
@RestController
@RequiredArgsConstructor
public class FollowController {
    private final FollowService followService;


    @GetMapping("/api/user/{userId}/follower") //나를 따르는 사람들
    public FromUserAllDto follower(@PathVariable Long userId){
        return followService.gerFromUsers(userId);
    }

    @GetMapping("/api/user/{userId}/following")
    public ToUserAllDto following(@PathVariable Long userId){
        return followService.getToUsers(userId);
    }

    @PostMapping("/api/follow/{toUserId}")
    public String follow(@PathVariable Long toUserId, @SessionAttribute Long userId){
        log.info("팔로우하기 : {}가 {}를 팔로우함",userId, toUserId);
        followService.makeFriend(toUserId, userId);
        return "ok";
    }

    @DeleteMapping("/api/follow/{toUserId}")
    public void followDelete(@PathVariable Long toUserId, @SessionAttribute Long userId){
        log.info("userId : {}가 {}를 언팔", userId, toUserId);
        followService.unFollow(toUserId, userId);
    }
}
