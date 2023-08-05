package project.kingstagram.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import project.kingstagram.domain.Post;

public interface PostRepository extends JpaRepository<Post, Long> {
}
