package project.kingstagram.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import project.kingstagram.domain.Post;
import project.kingstagram.post.dto.response.PostOneDto;
import project.kingstagram.post.dto.response.UserPostOneDto;

import java.util.List;

public interface PostRepository extends JpaRepository<Post, Long> {
//사용자가 특정 게시글을 눌렀을 때 -> 단건 조회
    @Query(
            value = "select new project.kingstagram.post.dto.response.PostOneDto" +
                    "(p.postId, p.postContent, p.imageUrl, p.postTime, u.userId, u.userNickname, count(l))" +
                    " from Post p join p.user u" +
                    " join Like l on l.post.postId = p.postId where p.postId = :postId" +
                    " group by p.postId"
    )
    PostOneDto findAllByPostId(@Param("postId") Long postId);

//사용자의 게시글과 사용자가 팔로우하는 사용자들의 게시글을 조회 + 사용자가 좋아요를 눌렀는지 체크하는 로직 필요
    @Query(
            value = "select new project.kingstagram.post.dto.response.UserPostOneDto" +
                    "(p.postId, p.postContent, p.imageUrl, p.postTime, u.userId, u.userNickname, count(distinct l), count(c))" +
                    " from Post p join p.user u" +
                    " left join Comment c on c.post = p" +
                    " left join Like l on l.post = p " +
                    " where u.userId in (select f.toUser from Follow f where f.fromUser.userId =:userId) or u.userId = :userId " +
                    " group by p.postId" +
                    " order by p.postTime desc"
    )
    List<UserPostOneDto> findAllPostByUserId(@Param("userId") Long userId, Pageable pageable);


    @Query(
            value = "select count(1) from Post p join p.user u" +
                    " where u.userId = :userId"
    )
    Integer findPostCountByUserId(@Param("userId") Long userId);
}
