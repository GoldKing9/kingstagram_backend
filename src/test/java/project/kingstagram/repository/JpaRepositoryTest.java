package project.kingstagram.repository;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import project.kingstagram.domain.Follow;
import project.kingstagram.domain.Users;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

@SpringBootTest
@DisplayName("JPA 연결 테스트")
@Transactional
@Rollback(value = false)
public class JpaRepositoryTest {

    @Autowired
    @PersistenceContext
    EntityManager em;
    @Test
    @DisplayName("팔로우 관계 테스트")
    void test(){
//        Users user1 = new Users();
//        Users user2 = new Users();
//        Users user3 = new Users();
//        Users user4 = new Users();
//
//        user1.setUserName("박경선");
//        user2.setUserName("최하록");
//        user3.setUserName("김지수");
//        user4.setUserName("고혁진");
//
//        em.persist(user1);
//        em.persist(user2);
//        em.persist(user3);
//        em.persist(user4);
//
//
//        Follow follow1 = new Follow(user1, user2);
//        Follow follow2 = new Follow(user2, user1);
//        Follow follow3 = new Follow(user1, user3);
//        Follow follow4 = new Follow(user1, user4);
//
//
//        em.persist(follow1);
//        em.persist(follow2);
//        em.persist(follow3);
//        em.persist(follow4);


    }
}
