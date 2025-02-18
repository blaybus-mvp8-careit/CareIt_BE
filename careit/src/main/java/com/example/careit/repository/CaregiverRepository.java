package com.example.careit.repository;

import com.example.careit.entity.Caregiver;
import com.example.careit.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CaregiverRepository extends JpaRepository<Caregiver, Long> {
    boolean existsByUser(User user);
    Optional<Caregiver> findByUserId(Long userId);  // User의 id로 Caregiver 조회
}
