package project.kingstagram.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import project.kingstagram.domain.Like;

public interface LikeRepository extends JpaRepository<Like, Long> {
}
