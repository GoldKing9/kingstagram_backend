package project.kingstagram.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import project.kingstagram.domain.Post;

public interface PostRespository extends JpaRepository<Post, Long> {


}
