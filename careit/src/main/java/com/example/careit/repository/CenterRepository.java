package com.example.careit.repository;

import com.example.careit.entity.Center;
import io.lettuce.core.dynamic.annotation.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface CenterRepository extends JpaRepository<Center, Long> {
    Optional<Center> findByName(String name);  //센터 이름으로 검색
    List<Center> findByRegisteredBy_Id(Long adminId);  //특정 관리자(adminId)가 등록한 센터 목록 조회
}
