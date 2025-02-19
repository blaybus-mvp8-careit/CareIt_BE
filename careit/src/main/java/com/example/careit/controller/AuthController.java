package com.example.careit.controller;

import com.example.careit.dto.*;
import com.example.careit.exception.InvalidPasswordException;
import com.example.careit.exception.UserNotFoundException;
import com.example.careit.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
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

    @PostMapping(value = "/signup", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<AuthResponseDto> signup(
            @ModelAttribute SignupRequestDto request,  // SignupRequestDto에서 request 부분을 받음
            @RequestParam(value = "photo", required = false) MultipartFile photo) {

        // photo는 SignupRequestDto 안에 포함시키고, 이를 signUp 메서드에 전달할 수 있도록 처리
        try {
            if (photo != null) {
                request.setPhoto(photo);  // 파일을 DTO에 설정
            }
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
        AuthResponseDto response = authService.refreshAccessToken(request.getRefreshToken());
        return ResponseEntity.ok(response);
    }

}