package project.kingstagram.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import project.kingstagram.domain.Follow;

public interface FollowRepository extends JpaRepository<Follow, Long> {

    @Query(
            value = "select count(1) from Follow f" +
                    " where f.fromUser = :userId"
    )
    Integer findFollowingCountByUserId(@Param("userId") Long userId);

    @Query(
            value = "select count(1) from Follow f" +
                    " where f.toUser = :userId"
    )
    Integer findFollowerCountByUserId(@Param("userId") Long userId);

}
