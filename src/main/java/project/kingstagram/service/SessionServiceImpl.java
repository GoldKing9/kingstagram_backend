package project.kingstagram.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import project.kingstagram.repository.UsersRepository;

import java.util.HashMap;
import java.util.UUID;

@Service
public class SessionServiceImpl implements SessionService {

    private final UsersRepository usersRepository;

    public HashMap<String,Long> dictionary = null;
    public SessionServiceImpl(UsersRepository usersRepository) {
        this.usersRepository = usersRepository;
        this.dictionary = new HashMap<String, Long>();
    }

    @Override
    public Long getUserId(String uuid) { // 세션 조회 (해시맵에서 uuid로 userId 조회)
        Long userId = dictionary.get(uuid);
        if (userId == null) {
            return -1L;
        } else {
            return userId;
        }
    }

    @Override
    public String InsertUuid(Long userId) throws RuntimeException { // 로그인 (uuid 생성)
        String uuid = UUID.randomUUID().toString();
        dictionary.put(uuid, userId);
        return uuid;
    }

    @Override
    public Long deleteUuid(String uuid) { // 로그아웃 (uuid 삭제)
        return dictionary.remove(uuid);
    }
}
