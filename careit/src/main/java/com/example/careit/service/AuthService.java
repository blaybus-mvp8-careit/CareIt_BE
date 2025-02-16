package com.example.careit.service;

import com.example.careit.dto.AuthResponseDto;
import com.example.careit.dto.LoginRequestDto;
import com.example.careit.dto.SignupRequestDto;
import com.example.careit.entity.User;
import com.example.careit.entity.Role;
import com.example.careit.repository.UserRepository;
import com.example.careit.util.JwtTokenProvider;
import com.example.careit.util.S3Uploader;
import io.jsonwebtoken.Claims;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Date;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final S3Uploader s3Uploader;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenProvider jwtTokenProvider;
    private final RefreshTokenService refreshTokenService;

    public AuthResponseDto signUp(SignupRequestDto request) throws IOException {
        String photoUrl = null;
        MultipartFile photoFile = request.getPhoto(); // MultipartFile 로 받음

        if (photoFile != null && !photoFile.isEmpty()) {
            photoUrl = s3Uploader.upload(photoFile, "profile"); // S3Uploader에 MultipartFile 넘기기
        }

        if (userRepository.existsByEmail(request.getEmail())) {
            throw new RuntimeException("이미 존재하는 이메일입니다.");
        }

        User user = userRepository.save(new User(
                null, request.getEmail(),
                passwordEncoder.encode(request.getPassword()),
                request.getRole(), new Date(),
                photoUrl
        ));

        String accessToken = jwtTokenProvider.createAccessToken(user.getId());
        String refreshToken = jwtTokenProvider.createRefreshToken(user.getId());

        refreshTokenService.saveRefreshToken(user.getId().toString(), refreshToken);

        return new AuthResponseDto(accessToken, refreshToken);
    }

    public AuthResponseDto login(LoginRequestDto request) {
        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new RuntimeException("이메일이 존재하지 않습니다."));

        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw new RuntimeException("비밀번호가 일치하지 않습니다.");
        }

        String accessToken = jwtTokenProvider.createAccessToken(user.getId());
        String refreshToken = jwtTokenProvider.createRefreshToken(user.getId());

        refreshTokenService.saveRefreshToken(user.getId().toString(), refreshToken);

        return new AuthResponseDto(accessToken, refreshToken);
    }

    public AuthResponseDto refreshAccessToken(String refreshToken) {
        Claims claims = jwtTokenProvider.getClaims(refreshToken);
        String userId = claims.getSubject();

        String storedRefreshToken = refreshTokenService.getRefreshToken(userId);
        if (!refreshToken.equals(storedRefreshToken)) {
            throw new RuntimeException("유효하지 않은 리프레시 토큰입니다.");
        }

        String newAccessToken = jwtTokenProvider.createAccessToken(Long.parseLong(userId));
        return new AuthResponseDto(newAccessToken, refreshToken);
    }

}

