package com.example.careit.controller;


import com.example.careit.dto.CaregiverAskDTO;
import com.example.careit.dto.CaregiverProfileDTO;
import com.example.careit.entity.User;
import com.example.careit.repository.UserRepository;
import com.example.careit.security.UserDetailsImpl;
import com.example.careit.service.CaregiverService;
import com.example.careit.util.JwtTokenProvider;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@Slf4j
@RestController
@RequestMapping("/api/caregiver")
@RequiredArgsConstructor
public class CaregiverController {
    private final JwtTokenProvider jwtTokenProvider;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CaregiverService caregiverService;

    // 프로필 등록 - 토큰
    @PostMapping("/profile")
    @PreAuthorize("hasRole('CAREGIVER')")  // 요양보호사만 접근 가능
    public ResponseEntity<CaregiverProfileDTO> registerProfile(
            @RequestBody CaregiverProfileDTO caregiverProfileDTO,
            HttpServletRequest request
    ) {
        String token = request.getHeader("Authorization").replace("Bearer ", "");
        Long userId = jwtTokenProvider.getUserIdFromToken(token);  // JWT에서 userId 추출

        log.info("User ID: {}", userId);

        CaregiverProfileDTO responseDTO = caregiverService.registerProfile(userId, caregiverProfileDTO);
        return ResponseEntity.ok(responseDTO);
    }

    // 프로필 조회 - 토큰
    @GetMapping("/profile")
    public ResponseEntity<CaregiverProfileDTO> getProfile(
            HttpServletRequest request
    ) {
        String token = request.getHeader("Authorization").replace("Bearer ", "");
        Long userId = jwtTokenProvider.getUserIdFromToken(token);  // JWT에서 userId 추출

        CaregiverProfileDTO caregiverProfileDTO = caregiverService.getProfile(userId);
        return ResponseEntity.ok(caregiverProfileDTO);
    }

    // 프로필 수정 - 토큰
    @PutMapping("/profile")
    @PreAuthorize("hasRole('CAREGIVER')")
    public ResponseEntity<CaregiverProfileDTO> updateProfile(
            @RequestBody CaregiverProfileDTO caregiverProfileDTO,
            HttpServletRequest request
    ) {
        String token = request.getHeader("Authorization").replace("Bearer ", "");
        Long userId = jwtTokenProvider.getUserIdFromToken(token);  // JWT에서 userId 추출

        CaregiverProfileDTO responseDTO = caregiverService.updateProfileDto(userId, caregiverProfileDTO);
        return ResponseEntity.ok(responseDTO);
    }

    // 프로필 삭제 - 토큰
    @DeleteMapping("/profile")
    @PreAuthorize("hasRole('CAREGIVER')")
    public ResponseEntity<String> deleteProfile(HttpServletRequest request
    ) {
        String token = request.getHeader("Authorization").replace("Bearer ", "");
        Long userId = jwtTokenProvider.getUserIdFromToken(token);  // JWT에서 userId 추출
        caregiverService.deleteProfile(userId);
        return ResponseEntity.ok("요양 보호사 프로필이 삭제되었습니다. ");
    }


    // 근무조건 등록 - 토큰
    @PostMapping("/workCondition")
    @PreAuthorize("hasRole('CAREGIVER')") // 요양보호사만 접근 가능
    public ResponseEntity<CaregiverAskDTO> resisterWorkCondition(
            @RequestBody CaregiverAskDTO caregiverAskDTO,
            HttpServletRequest request
    ) {
        String token = request.getHeader("Authorization").replace("Bearer ", "");
        Long userId = jwtTokenProvider.getUserIdFromToken(token);  // JWT에서 userId 추출

        CaregiverAskDTO responseDTO = caregiverService.registerWorkCondition(userId, caregiverAskDTO);
        return ResponseEntity.ok(responseDTO);
    }

    // 근무 조건 조회 - 토큰
    @GetMapping("/workCondition")
    public ResponseEntity<CaregiverAskDTO> updateWorkCondition(
            HttpServletRequest request
    ) {
        String token = request.getHeader("Authorization").replace("Bearer ", "");
        Long userId = jwtTokenProvider.getUserIdFromToken(token);  // JWT에서 userId 추출

        CaregiverAskDTO responseDTO = caregiverService.getWorkCondition(userId);
        return ResponseEntity.ok(responseDTO);
    }

    // 근무 조건 수정 - 토큰
    @PutMapping("/workCondition")
    public ResponseEntity<CaregiverAskDTO> updateWorkCondition(
            @RequestBody CaregiverAskDTO caregiverAskDTO,
            HttpServletRequest request
    ) {
        String token = request.getHeader("Authorization").replace("Bearer ", "");
        Long userId = jwtTokenProvider.getUserIdFromToken(token);  // JWT에서 userId 추출

        CaregiverAskDTO responseDTO = caregiverService.updateWorkCondition(userId, caregiverAskDTO);
        return ResponseEntity.ok(responseDTO);
    }

    // 근무 조건 삭제 - 토큰
    @DeleteMapping("/workCondition")
    public ResponseEntity<String> deleteWorkCondition(HttpServletRequest request
    ) {
        String token = request.getHeader("Authorization").replace("Bearer ", "");
        Long userId = jwtTokenProvider.getUserIdFromToken(token);  // JWT에서 userId 추출

        caregiverService.deleteWorkCondition(userId);
        return ResponseEntity.ok("요양 보호사 근무 조건이 삭제되었습니다. ");
    }
}
