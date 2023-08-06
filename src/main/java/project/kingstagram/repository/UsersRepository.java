package project.kingstagram.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import project.kingstagram.domain.Users;

public interface UsersRepository extends JpaRepository<Users,Long> {
}
