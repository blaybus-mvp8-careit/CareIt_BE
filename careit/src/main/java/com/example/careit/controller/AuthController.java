package com.example.careit.controller;

import com.example.careit.dto.*;
import com.example.careit.exception.InvalidPasswordException;
import com.example.careit.exception.UserNotFoundException;
import com.example.careit.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Map;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/signup")
    public ResponseEntity<AuthResponseDto> signup(
            @RequestPart SignupRequestDto request,  // SignupRequestDto에서 request 부분을 받음
            @RequestPart(required = false) MultipartFile photo) {

        try {
            AuthResponseDto response = authService.signUp(request, photo);
            return ResponseEntity.ok(response);
        } catch (IOException e) {
            return ResponseEntity.internalServerError().body(null);
        }
    }


    @PostMapping("/login")
    public ResponseEntity<AuthResponseDto> login(@RequestBody LoginRequestDto request) {
        AuthResponseDto response = authService.login(request);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/refresh")
    public ResponseEntity<AuthResponseDto> refreshAccessToken(@RequestBody TokenRefreshRequestDto request) {
        return ResponseEntity.ok(authService.refreshAccessToken(request.getRefreshToken()));
    }

}