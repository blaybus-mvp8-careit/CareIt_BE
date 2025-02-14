package com.example.careit.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/test")
@RequiredArgsConstructor
public class RedisController {
    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    @GetMapping("/set")
    public ResponseEntity<?> setKeyValue() {
        ValueOperations<String, String> vop = redisTemplate.opsForValue();
        // 예시 데이터: 유저 로그인 후 발급된 refresh token 저장
        String userId = "user123";  // 로그인한 유저의 ID
        String refreshToken = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1c2VySWQiOiJ1c2VyMTIzIn0.xxxxxxxx";  // 발급된 refresh token

        // Redis에 저장 (key: userId, value: refreshToken)
        vop.set(userId, refreshToken);

        return new ResponseEntity<>( HttpStatus.CREATED);
    }

    @GetMapping("/get/{key}")
    public ResponseEntity<?> getValueFromKey(@PathVariable String key) {
        ValueOperations<String, String> vop = redisTemplate.opsForValue();
        // Redis에서 refresh token 가져오기
        String value = vop.get(key);

        // refresh token이 없다면 null을 반환
        if (value == null) {
            return new ResponseEntity<>("No refresh token found for user", HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(value, HttpStatus.OK);
    }
}
