package com.example.careit.repository;

import com.example.careit.entity.Caregiver;
import com.example.careit.entity.CaregiverAsk;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CaregiverAskRepository extends JpaRepository<CaregiverAsk, Long> {
    boolean existsByCareGiver(Caregiver careGiver); // 요양보호사 프로필 정보

    Optional<CaregiverAsk> findByCareGiver(Caregiver caregiver);
}
