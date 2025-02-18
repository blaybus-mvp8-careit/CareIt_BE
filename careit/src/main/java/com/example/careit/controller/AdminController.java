package com.example.careit.controller;

import com.example.careit.dto.AdminProfileDto;
import com.example.careit.service.AdminService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class AdminController {
    private final AdminService adminService;

    @PostMapping("/profile/admin")  // 관리자 프로필 등록
    public ResponseEntity<AdminProfileDto> createAdmin(
            @RequestParam @NotNull Long userId,
            @RequestParam Long centerId, //@NotNull
            @RequestBody @Valid AdminProfileDto dto) {
        return ResponseEntity.ok(adminService.createAdmin(userId, centerId, dto));
    }

    @GetMapping("/profile/{adminId}")  //관리자 프로필 조회
    public ResponseEntity<AdminProfileDto> getAdminProfile(@PathVariable Long adminId) {
        return ResponseEntity.ok(adminService.getAdminProfile(adminId));
    }

    @PutMapping("/profile/{adminId}")  //관리자 프로필 수정
    public ResponseEntity<AdminProfileDto> updateAdminProfile(
            @PathVariable Long adminId,
            @RequestBody @Valid AdminProfileDto dto) {
        return ResponseEntity.ok(adminService.updateAdminProfile(adminId, dto));
    }

    @DeleteMapping("/profile/{adminId}")  //관리자 프로필 삭제
    public ResponseEntity<Void> deleteAdmin(@PathVariable Long adminId) {
        adminService.deleteAdmin(adminId);
        return ResponseEntity.noContent().build();
    }
}

