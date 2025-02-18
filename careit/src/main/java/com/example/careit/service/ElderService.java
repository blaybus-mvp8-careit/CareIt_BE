package com.example.careit.service;

import com.example.careit.dto.ElderDTO;
import com.example.careit.entity.Admin;
import com.example.careit.entity.Elder;
import com.example.careit.repository.AdminRepository;
import com.example.careit.repository.ElderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ElderService {
    private final ElderRepository elderRepository;
    private final AdminRepository adminRepository;

    // 어르신 정보 등록
    @Transactional
    public ElderDTO registerElder(Long adminId, ElderDTO elderDTO) {
        Admin admin = adminRepository.findById(adminId)
                .orElseThrow(() -> new IllegalArgumentException("관리자를 찾을 수 없습니다."));
        Elder elder = elderDTO.toEntity(admin);
        elder = elderRepository.save(elder);

        return ElderDTO.fromEntity(elder);
    }
    // 특정 어르신 정보 조회
    public ElderDTO getElderById(Long elderId) {
        Elder elder = elderRepository.findById(elderId)
                .orElseThrow(() -> new IllegalArgumentException("해당 어르신 프로필을 찾을 수 없습니다."));
        return ElderDTO.fromEntity(elder);
    }

    // 어르신 정보 수정
    @Transactional
    public ElderDTO updateElder(Long adminId, Long elderId, ElderDTO elderDTO) {
        Elder elder = elderRepository.findById(elderId)
                .orElseThrow(() -> new IllegalArgumentException("해당 어르신 프로필을 찾을 수 없습니다."));
        elderDTO.updateEntity(elder);
        elderRepository.save(elder);

        return ElderDTO.fromEntity(elder);
    }

    // 어르신 삭제
    @Transactional
    public void deleteElder(Long elderId) {
        Elder elder = elderRepository.findById(elderId)
                .orElseThrow(() -> new IllegalArgumentException("해당 어르신 프로필을 찾을 수 없습니다."));
        elderRepository.delete(elder);
    }

    // 특정 관리자가 등록한 어르신 목록 전체 조회
    public List<ElderDTO> getEldersByAdminId(Long adminId) {
        return elderRepository.findByAdminId(adminId)
                .stream()
                .map(ElderDTO::fromEntity)
                .collect(Collectors.toList());
    }
}
