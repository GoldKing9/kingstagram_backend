package project.kingstagram.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import project.kingstagram.domain.Post;
import project.kingstagram.dto.postGet.MiniDto;
import project.kingstagram.dto.postGet.PostOneDto;

public interface PostRepository extends JpaRepository<Post, Long> {

    @Query(
            value = "select new project.kingstagram.dto.postGet.MiniDto" +
                    "(p.postId, p.postContent, p.imageUrl, p.postTime, u.userId, u.userNickname, count(l))" +
                    " from Post p join p.user u" +
                    " join Like l on l.post.postId = p.postId where p.postId = :postId" +
                    " group by p.postId"
    )
    MiniDto findAllByPostId(@Param("postId") Long postId);
}
