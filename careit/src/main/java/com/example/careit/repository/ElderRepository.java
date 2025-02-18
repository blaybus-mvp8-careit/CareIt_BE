package com.example.careit.repository;

import com.example.careit.entity.Elder;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ElderRepository extends JpaRepository<Elder, Long> {

    // 특정 관리자가 등록한 어르신 정보 전체 조회
    List<Elder> findByAdminId(Long adminId);
}
