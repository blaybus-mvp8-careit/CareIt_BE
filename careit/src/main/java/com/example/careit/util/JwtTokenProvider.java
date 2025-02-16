package com.example.careit.util;

import io.jsonwebtoken.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Date;

@Slf4j
@Component
public class JwtTokenProvider {

    @Value("${jwt.secret}")
    private String secretKey;

    private final long ACCESS_TOKEN_VALIDITY = 24 * 60 * 60 * 1000L; // 1일
    private final long REFRESH_TOKEN_VALIDITY = 7 * 24 * 60 * 60 * 1000L; // 7일

    // Access Token 생성
    public String createAccessToken(Long userId) {
        return Jwts.builder()
                .setSubject(userId.toString())
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + ACCESS_TOKEN_VALIDITY))
                .signWith(SignatureAlgorithm.HS256, secretKey)
                .compact();
    }

    // Refresh Token 생성
    public String createRefreshToken(Long userId) {
        return Jwts.builder()
                .setSubject(userId.toString())
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + REFRESH_TOKEN_VALIDITY))
                .signWith(SignatureAlgorithm.HS256, secretKey)
                .compact();
    }

    // 토큰 검증
    public boolean validateToken(String token) {
        try {
            JwtParser jwtParser = Jwts.parser()  // parserBuilder를 사용해서 JwtParser 생성
                    .setSigningKey(secretKey)
                    .build();
            jwtParser.parseClaimsJws(token);  // parseClaimsJws 호출
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    // 토큰에서 이메일 추출
    public String getEmailFromToken(String token) {
        Claims claims = Jwts.parser()  // parser() 사용
                .setSigningKey(secretKey)
                .build()
                .parseClaimsJws(token)  // parseClaimsJws 호출
                .getBody();
        return claims.getSubject();  // Claims에서 subject(사용자 ID) 추출
    }

    // Claims 추출
    public Claims getClaims(String token) {
        JwtParser jwtParser = Jwts.parser()  // parserBuilder를 사용해서 JwtParser 생성
                .setSigningKey(secretKey)
                .build();
        return jwtParser.parseClaimsJws(token).getBody();  // parseClaimsJws 호출
    }
}
