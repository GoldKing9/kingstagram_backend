package project.kingstagram.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import project.kingstagram.domain.Follow;
import project.kingstagram.user.dto.response.FromUserDto;
import project.kingstagram.user.dto.response.ToUserDto;

import java.util.List;

public interface FollowRepository extends JpaRepository<Follow, Long> {
    @Query(value =
           "select new project.kingstagram.user.dto.response.FromUserDto" +
                   "(f.fromUser.userId, f.fromUser.userName, f.fromUser.userNickname)" +
                   " from Follow f where f.toUser.userId=:userId"
    )
    List<FromUserDto> findFromUserAllByUserId(@Param("userId") Long userId);

    @Query(value =
        "select new project.kingstagram.user.dto.response.ToUserDto" +
                "(f.toUser.userId, f.toUser.userName, f.toUser.userNickname)" +
                " from Follow f where f.fromUser.userId=:userId"
    )
    List<ToUserDto> findToUserAllByUserId(@Param("userId") Long userId);

    @Transactional
    @Modifying
    @Query(value =
        "delete from Follow f where f.toUser.userId = :toUserId and f.fromUser.userId = :userId"
    )
    void deleteToUserByUserId(@Param("toUserId")Long toUserId,@Param("userId") Long userId);
}
