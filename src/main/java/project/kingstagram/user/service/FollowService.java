package project.kingstagram.user.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import project.kingstagram.domain.Follow;
import project.kingstagram.domain.Users;
import project.kingstagram.repository.FollowRepository;
import project.kingstagram.repository.UsersRepository;
import project.kingstagram.user.dto.response.FromUserAllDto;
import project.kingstagram.user.dto.response.FromUserDto;
import project.kingstagram.user.dto.response.ToUserAllDto;
import project.kingstagram.user.dto.response.ToUserDto;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class FollowService {
    private final FollowRepository followRepository;
    private final UsersRepository usersRepository;
    @Transactional
    public void makeFriend(Long toUserId, Long userId) {

        Users toUser = usersRepository.findById(toUserId).get();
        Users fromUser = usersRepository.findById(userId).get();

        Follow follow = new Follow(toUser,fromUser);
        followRepository.save(follow);
    }

    public void unFollow(Long toUserId, Long userId) {
        followRepository.deleteToUserByUserId(toUserId, userId);
    }

    public FromUserAllDto gerFromUsers(Long userId) {
        List<FromUserDto> followerAll = followRepository.findFromUserAllByUserId(userId);
        return FromUserAllDto.builder()
                .fromUsers(followerAll)
                .build();
    }

    public ToUserAllDto getToUsers(Long userId) {
        List<ToUserDto> followingAll = followRepository.findToUserAllByUserId(userId);
        return ToUserAllDto.builder()
                .toUsers(followingAll)
                .build();
    }
}
