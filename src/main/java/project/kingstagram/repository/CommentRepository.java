package project.kingstagram.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import project.kingstagram.domain.Comment;
import project.kingstagram.domain.Post;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment,Long> {
    List<Comment> findAllByPost(Post post);
}
