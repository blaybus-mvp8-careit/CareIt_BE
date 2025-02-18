package com.example.careit.service;

import com.example.careit.dto.AdminProfileDto;
import com.example.careit.entity.Admin;
import com.example.careit.entity.Center;
import com.example.careit.entity.User;
import com.example.careit.repository.AdminRepository;
import com.example.careit.repository.CenterRepository;
import com.example.careit.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Transactional
public class AdminService {
    private final AdminRepository adminRepository;
    private final UserRepository userRepository;
    private final CenterRepository centerRepository;
    private Logger log;

    // 관리자 프로필 등록
    public AdminProfileDto createAdmin(Long userId, Long centerId, AdminProfileDto dto) {
        // 이미 등록된 관리자인지 확인
        if (adminRepository.existsByUserId(userId)) {
            throw new IllegalStateException("해당 유저는 이미 관리자 프로필을 가지고 있습니다.");
        }
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException("해당 유저를 찾을 수 없습니다."));
        Center center = centerRepository.findById(centerId)
                .orElseThrow(() -> new EntityNotFoundException("해당 센터를 찾을 수 없습니다."));

        Admin admin = Admin.builder()
                .user(user)
                .center(center) // 수락 후 센터로 연결
                .address(dto.getAddress())
                .name(dto.getName())
                .phone(dto.getPhone())
                .build();

        adminRepository.save(admin);

        return AdminProfileDto.builder()
                .id(admin.getId())
                .centerId(admin.getCenter().getId())
                .address(admin.getAddress())
                .name(admin.getName())
                .phone(admin.getPhone())
                .build();
    }

    // 관리자 프로필 조회
    public AdminProfileDto getAdminProfile(Long adminId) {
        Admin admin = adminRepository.findById(adminId)
                .orElseThrow(() -> new EntityNotFoundException("관리자를 찾을 수 없습니다."));

        return AdminProfileDto.builder()
                .id(admin.getId())
                .centerId(admin.getCenter().getId())
                .address(admin.getAddress())
                .name(admin.getName())
                .phone(admin.getPhone())
                .build();
    }

    // 관리자 프로필 수정
    @Transactional
    public AdminProfileDto updateAdminProfile(Long adminId, AdminProfileDto dto) {
        Admin admin = adminRepository.findById(adminId)
                .orElseThrow(() -> new EntityNotFoundException("관리자를 찾을 수 없습니다."));

        admin.setName(dto.getName());
        admin.setAddress(dto.getAddress());
        admin.setPhone(dto.getPhone());

        //adminRepository.save(admin); //변경된 관리자 정보 저장하여 DB 반영

        return AdminProfileDto.builder()
                .id(admin.getId())
                .centerId(admin.getCenter().getId())
                .address(admin.getAddress())
                .name(admin.getName())
                .phone(admin.getPhone())
                .build();
    }

    // 관리자 프로필 삭제
    public void deleteAdmin(Long adminId) {
        Admin admin = adminRepository.findById(adminId)
                .orElseThrow(() -> new EntityNotFoundException("관리자를 찾을 수 없습니다."));

        adminRepository.delete(admin);
    }
}
