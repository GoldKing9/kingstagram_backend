package project.kingstagram.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import project.kingstagram.domain.Like;

public interface LikeRepository extends JpaRepository<Like, Long> {
    @Transactional
    @Modifying
    @Query(value =
            "delete from Like l where l.post.postId = :postId and l.user.userId = :userId"
    )
    void deleteLike(@Param("postId")Long postId, @Param("userId") Long userId);

    @Query(
            value = "select count(1) from Like l "+
            "where l.post.postId = :postId"
    )Long likeCount(@Param("postId")Long postId);
}
