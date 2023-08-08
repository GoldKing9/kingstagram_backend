package project.kingstagram.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import project.kingstagram.domain.Users;

import java.util.List;

public interface UsersRepository extends JpaRepository<Users, Long> {
    List<Users> findByUserEmailAndUserPw(@Param("userEmail") String userEmail, @Param("userPw") String userPw);
// List로 구현 안 한다면 Users 객체나 null 값이 반환됨
// List로 구현했으면 @Param으로 DB에서 해당 값 조회 후 일치하는 게 없을 때 리스트에 해당하는 객체가 안 들어와서 빈 리스트가 됨(size가 0인 리스트)

}