package project.kingstagram.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import project.kingstagram.domain.Comment;
import project.kingstagram.post.dto.response.CommentDto;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {

    @Query(value =
            "select new project.kingstagram.post.dto.response.CommentDto" +
                    "(c.commentId, c.commentContent, c.commentTime, u.userId, u.userNickname)"+
                    " from Comment c join c.user u where c.post.postId = :postId"
    )
    List<CommentDto> findAllByPostId(@Param("postId") Long postId);
}
