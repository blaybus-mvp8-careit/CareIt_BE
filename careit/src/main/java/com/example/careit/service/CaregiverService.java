package com.example.careit.service;


import com.example.careit.dto.CaregiverAskDTO;
import com.example.careit.dto.CaregiverProfileDTO;
import com.example.careit.entity.Caregiver;
import com.example.careit.entity.CaregiverAsk;
import com.example.careit.entity.Role;
import com.example.careit.entity.User;
import com.example.careit.repository.CaregiverAskRepository;
import com.example.careit.repository.CaregiverRepository;
import com.example.careit.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class CaregiverService {

    private final CaregiverRepository caregiverRepository;
    private final UserRepository userRepository;
    private final CaregiverAskRepository caregiverAskRepository;

    // 프로필 등록
    public CaregiverProfileDTO registerProfile(Long userId, CaregiverProfileDTO caregiverProfileDTO) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("사용자를 찾을 수 없습니다."));

        if (user.getRole() != Role.CAREGIVER) {
            throw new IllegalArgumentException("요양보호사만 프로필을 등록할 수 있습니다.");
        }
        if (caregiverRepository.existsByUser(user)) {
            throw new IllegalArgumentException("이미 등록된 프로필이 있습니다.");
        }

        Caregiver caregiver = new Caregiver();
        caregiver.setUser(user);
        caregiverProfileDTO.updateEntity(caregiver);

        caregiverRepository.save(caregiver);
        return CaregiverProfileDTO.from(caregiver);
    }

    // 프로필 조회
    public CaregiverProfileDTO getProfile(Long userId) { // userId로 요양보호사 프로필 조회
        log.info("조회하려는 caregiver ID: {}", userId);
        Caregiver caregiver = caregiverRepository.findByUserId(userId)
                .orElseThrow(() -> new IllegalArgumentException("등록된 프로필이 없습니다."));
        log.info("조회된 caregiver: {}", caregiver);
        return CaregiverProfileDTO.from(caregiver);
    }

    //프로필 수정
    @Transactional
    public CaregiverProfileDTO updateProfileDto(Long id, CaregiverProfileDTO caregiverProfileDTO) { // // userId로 요양보호사 프로필 수정
        Caregiver caregiver = caregiverRepository.findByUserId(id)
                .orElseThrow(() -> new IllegalArgumentException("프로필이 존재하지 않습니다."));
        caregiverProfileDTO.updateEntity(caregiver);
        return CaregiverProfileDTO.from(caregiver);
    }

    //프로필 삭제
    @Transactional
    public void deleteProfile(Long id) {
        Caregiver caregiver = caregiverRepository.findByUserId(id)
                .orElseThrow(() -> new IllegalArgumentException("삭제할 프로필이 없습니다."));
        caregiverRepository.delete(caregiver);
    }

    // 근무 조건 등록하기
    public CaregiverAskDTO registerWorkCondition(Long id, CaregiverAskDTO caregiverAskDTO) {
        Caregiver caregiver = caregiverRepository.findByUserId(id)
                .orElseThrow(() -> new IllegalArgumentException("요양보호사 정보를 찾을 수 없습니다."));
        if (caregiverAskRepository.existsByCareGiver(caregiver)) {
            throw new IllegalArgumentException("등록된 근무 조건이 존재합니다.");
        }

        CaregiverAsk caregiverAsk = caregiverAskDTO.toEntity();
        caregiverAsk.setCareGiver(caregiver);
        caregiverAskRepository.save(caregiverAsk);

        return CaregiverAskDTO.from(caregiverAsk);
    }

    // 근무 조건 조회
    public CaregiverAskDTO getWorkCondition(Long id) {
        Caregiver caregiver = caregiverRepository.findByUserId(id)
                .orElseThrow(() -> new IllegalArgumentException("요양보호사 정보를 찾을 수 없습니다."));
        CaregiverAsk caregiverAsk = caregiverAskRepository.findByCareGiver(caregiver)
                .orElseThrow(() -> new IllegalArgumentException("등록된 근무 조건이 없습니다."));

        return CaregiverAskDTO.from(caregiverAsk);
    }

    // 근무 조건 수정
    @Transactional
    public CaregiverAskDTO updateWorkCondition(Long id, CaregiverAskDTO caregiverAskDTO) {
        Caregiver caregiver = caregiverRepository.findByUserId(id)
                .orElseThrow(() -> new IllegalArgumentException("요양보호사 정보를 찾을 수 없습니다."));
        CaregiverAsk caregiverAsk = caregiverAskRepository.findByCareGiver(caregiver)
                .orElseThrow(() -> new IllegalArgumentException("등록된 근무 조건이 없습니다."));

        // 기존 근무 조건 업데이트
        caregiverAsk.setWorkArea(caregiverAskDTO.getWorkArea());
        caregiverAsk.setWorkDays(caregiverAskDTO.getWorkDays());
        caregiverAsk.setWorkHours(caregiverAskDTO.getWorkHours());
        caregiverAsk.setDesiredSalary(caregiverAskDTO.getDesiredSalary());
        caregiverAsk.setPersonalityKeywords(caregiverAskDTO.getPersonalityKeywords());
        caregiverAsk.setStrongService(caregiverAskDTO.getStrongService());

        caregiverAskRepository.save(caregiverAsk);

        return CaregiverAskDTO.from(caregiverAsk);
    }

    // 근무 조건 삭제
    public void deleteWorkCondition(Long id) {
        Caregiver caregiver = caregiverRepository.findByUserId(id)
                .orElseThrow(() -> new IllegalArgumentException("요양보호사 정보를 찾을 수 없습니다. "));
        CaregiverAsk caregiverAsk = caregiverAskRepository.findByCareGiver(caregiver)
                .orElseThrow(() -> new IllegalArgumentException("등록된 근무 조건이 없습니다."));

        caregiverAskRepository.delete(caregiverAsk);
    }



// 각 DTO로 옮김
//    // Caregiver 정보를 DTO로 변환
//    private CaregiverProfileDTO convertToDTO(Caregiver caregiver) {
//        return new CaregiverProfileDTO(
//                caregiver.getName(),
//                caregiver.getPhone(),
//                caregiver.getCertificateName(),
//                caregiver.getCertificateNumber(),
//                caregiver.getAddress(),
//                caregiver.getOwnsCar(),
//                caregiver.getDementiaTraining(),
//                caregiver.getIntroduction(),
//                caregiver.getMajorExperience(),
//                caregiver.getExperienceYears()
//        );
//    }
//
//    // Caregiver 객체 업데이트
//    private void updateCaregiverFields(Caregiver caregiver, CaregiverProfileDTO requestDTO) {
//        caregiver.setName(requestDTO.getName());
//        caregiver.setPhone(requestDTO.getPhone());
//        caregiver.setCertificateName(requestDTO.getCertificateName());
//        caregiver.setCertificateNumber(requestDTO.getCertificateNumber());
//        caregiver.setAddress(requestDTO.getAddress());
//        caregiver.setOwnsCar(requestDTO.getOwnsCar());
//        caregiver.setDementiaTraining(requestDTO.getDementiaTraining());
//        caregiver.setIntroduction(requestDTO.getIntroduction());
//        caregiver.setMajorExperience(requestDTO.getMajorExperience());
//        caregiver.setExperienceYears(requestDTO.getExperienceYears());
//    }
//
//    // 근무 조건을 DTO로 변환
//    private CaregiverAskDTO convertToDTO(CaregiverAsk caregiverAsk) {
//        return new CaregiverAskDTO(
//                caregiverAsk.getWorkArea(),
//                caregiverAsk.getWorkDays(),
//                caregiverAsk.getWorkHours(),
//                caregiverAsk.getDesiredSalary(),
//                caregiverAsk.getPersonalityKeywords(),
//                caregiverAsk.getStrongService()
//        );
//    }
//
//    // 근무 조건 객체 업데이트
//    private void updateCaregiverAskFields(CaregiverAsk caregiverAsk, CaregiverAskDTO requestDTO) {
//        caregiverAsk.setWorkArea(requestDTO.getWorkArea());
//        caregiverAsk.setWorkDays(requestDTO.getWorkDays());
//        caregiverAsk.setWorkHours(requestDTO.getWorkHours());
//        caregiverAsk.setDesiredSalary(requestDTO.getDesiredSalary());
//        caregiverAsk.setPersonalityKeywords(requestDTO.getPersonalityKeywords());
//        caregiverAsk.setStrongService(requestDTO.getStrongService());
//    }
}
