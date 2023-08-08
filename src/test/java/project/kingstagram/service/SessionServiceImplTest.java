package project.kingstagram.service;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import project.kingstagram.repository.UsersRepository;
import project.kingstagram.user.service.SessionServiceImpl;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

class SessionServiceImplTest {

    SessionServiceImpl sessionServiceimpl;
    UsersRepository usersRepository;
    @BeforeEach
    public void beforeEach() {
        sessionServiceimpl = new SessionServiceImpl(usersRepository);
    }

    @Test
    @DisplayName("다중 세션에서 userId는 같아야 함")
    void getUserId() {
        Long userId1 = sessionServiceimpl.getUserId(sessionServiceimpl.insertUuid(1L));
        Long userId2 = sessionServiceimpl.getUserId(sessionServiceimpl.insertUuid(1L));
        assertThat(userId1).isEqualTo(userId2);
    }
    @Test()
    @DisplayName("다중 세션에서 같은 userId에 대해 uuid는 달라야 함")
    void getUserId2() {
        String uuid1 = sessionServiceimpl.insertUuid(1L);
        String uuid2 = sessionServiceimpl.insertUuid(1L);
        assertThat(uuid1).isNotEqualTo(uuid2);
    }

    @Test
    @DisplayName("같은 userId에 대해 uuid를 생성할 때마다 다른 값이 만들어져야 함")
    void insertUuid() {
        String uuid1 = sessionServiceimpl.insertUuid(1L);
        String uuid2 = sessionServiceimpl.insertUuid(1L);
        assertThat(uuid1).isNotEqualTo(uuid2);
    }

    @Test
    @DisplayName("uuid가 삭제되면 조회할 수 없어야 함")
    void deleteUuid() {
        String uuid1 = sessionServiceimpl.insertUuid(1L);
        sessionServiceimpl.deleteUuid(uuid1);
        assertThat(sessionServiceimpl.getUserId(uuid1)).isSameAs(-1L);
    }
}