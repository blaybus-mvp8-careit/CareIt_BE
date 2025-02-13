package com.example.careit.controller;

import com.example.careit.dto.AuthResponseDto;
import com.example.careit.dto.LoginRequestDto;
import com.example.careit.dto.SignupRequestDto;
import com.example.careit.dto.TokenRefreshRequestDto;
import com.example.careit.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/signup")
    public ResponseEntity<AuthResponseDto> signup(@RequestBody SignupRequestDto request) {
        return ResponseEntity.ok(authService.signup(request));
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponseDto> login(@RequestBody LoginRequestDto request) {
        return ResponseEntity.ok(authService.login(request));
    }

    @PostMapping("/refresh")
    public ResponseEntity<AuthResponseDto> refreshAccessToken(@RequestBody TokenRefreshRequestDto request) {
        return ResponseEntity.ok(authService.refreshAccessToken(request.getRefreshToken()));
    }

}

