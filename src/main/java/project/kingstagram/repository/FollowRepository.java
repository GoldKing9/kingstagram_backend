package project.kingstagram.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import project.kingstagram.domain.Follow;

public interface FollowRepository extends JpaRepository<Follow, Long> {
}
