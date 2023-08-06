package project.kingstagram.service;

import org.springframework.stereotype.Service;

import java.util.HashMap;

@Service
public interface SessionService {
    public HashMap<String,Long> dictionary = null;

    public Long getUserId(String uuid); // 세션 조회 (해시맵에서 uuid로 userId 조회)

    public String insertUuid(Long userId) throws RuntimeException; // 로그인 (uuid 생성)
    public Long deleteUuid(String uuid); // 로그아웃 (uuid 삭제)
}
