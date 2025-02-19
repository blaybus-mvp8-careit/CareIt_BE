package com.example.careit.service;

import com.example.careit.dto.CenterDto;
import com.example.careit.entity.Center;
import com.example.careit.repository.CenterRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
@RequiredArgsConstructor
public class CenterService {
    private final CenterRepository centerRepository;

    // 센터 프로필 등록
    public CenterDto createCenter(CenterDto dto) {
        Center center = Center.builder()
                .name(dto.getName())
                .address(dto.getAddress())
                .phone(dto.getPhone())
                .hasBathVehicle(dto.getHasBathVehicle())
                .centerGrade(dto.getCenterGrade()) // 선택 입력
                .operatingPeriod(dto.getOperatingPeriod()) // 선택 입력
                .welfareBenefits(dto.getWelfareBenefits()) // 선택 입력
                .centerPhoto(dto.getCenterPhoto()) // 선택 입력
                .introduction(dto.getIntroduction()) // 선택 입력
                .registeredAt(new Date()) // 현재 시간 기준 등록
                .build();

        Center savedCenter = centerRepository.save(center);
        return convertToDto(savedCenter);
    }

    // 센터 프로필 조회
    public CenterDto getCenterById(Long id) {
        Center center = centerRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("센터를 찾을 수 없습니다."));
        return convertToDto(center);
    }

    // 센터 프로필 수정
    public CenterDto updateCenter(Long id, CenterDto dto) {
        Center center = centerRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("센터를 찾을 수 없습니다."));

        center.setName(dto.getName());
        center.setAddress(dto.getAddress());
        center.setPhone(dto.getPhone());
        center.setHasBathVehicle(dto.getHasBathVehicle());
        center.setCenterGrade(dto.getCenterGrade());
        center.setOperatingPeriod(dto.getOperatingPeriod());
        center.setWelfareBenefits(dto.getWelfareBenefits());
        center.setCenterPhoto(dto.getCenterPhoto());
        center.setIntroduction(dto.getIntroduction());

        Center updatedCenter = centerRepository.save(center);
        return convertToDto(updatedCenter);
    }

    // 센터 프로필 삭제
    public void deleteCenter(Long id) {
        if (!centerRepository.existsById(id)) {
            throw new EntityNotFoundException("센터를 찾을 수 없습니다.");
        }
        centerRepository.deleteById(id);
    }

    //엔티티 → DTO 변환 메서드
    private CenterDto convertToDto(Center center) {
        return CenterDto.builder()
                .id(center.getId())
                .name(center.getName())
                .address(center.getAddress())
                .phone(center.getPhone())
                .hasBathVehicle(center.getHasBathVehicle())
                .centerGrade(center.getCenterGrade())
                .operatingPeriod(center.getOperatingPeriod())
                .welfareBenefits(center.getWelfareBenefits())
                .centerPhoto(center.getCenterPhoto())
                .introduction(center.getIntroduction())
                .build();
    }
}