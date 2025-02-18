package com.example.careit.repository;

import com.example.careit.entity.Admin;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AdminRepository extends JpaRepository<Admin, Long> {
    Optional<Admin> findById(Long id);
    boolean existsByUserId(Long userId);
}
