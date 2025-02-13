package com.example.careit.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
@RequiredArgsConstructor
public class RefreshTokenService {

    private final RedisTemplate<String, Object> redisTemplate;
    private static final long REFRESH_TOKEN_EXPIRATION = 7 * 24 * 60 * 60; // 7일 (초 단위)

    // 🔹 Redis에 저장
    public void saveRefreshToken(String userId, String refreshToken) {
        redisTemplate.opsForValue().set("refresh:" + userId, refreshToken, REFRESH_TOKEN_EXPIRATION, TimeUnit.SECONDS);
    }

    // 🔹 Redis에서 조회
    public String getRefreshToken(String userId) {
        return (String) redisTemplate.opsForValue().get("refresh:" + userId);
    }

    // 🔹 Redis에서 삭제
    public void deleteRefreshToken(String userId) {
        redisTemplate.delete("refresh:" + userId);
    }
}

