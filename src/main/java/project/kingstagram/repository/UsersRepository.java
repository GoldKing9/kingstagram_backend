package project.kingstagram.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import project.kingstagram.domain.Users;

import java.util.List;

public interface UsersRepository extends JpaRepository<Users, Long> {
    List<Users> findByUserEmailAndUserPw(String userEmail, String userPw);
}