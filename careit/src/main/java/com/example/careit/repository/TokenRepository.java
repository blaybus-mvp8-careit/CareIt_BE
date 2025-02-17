package com.example.careit.repository;

import com.example.careit.entity.Token;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TokenRepository extends JpaRepository<Token, Long> {
    // 필요한 쿼리 메서드 추가
}