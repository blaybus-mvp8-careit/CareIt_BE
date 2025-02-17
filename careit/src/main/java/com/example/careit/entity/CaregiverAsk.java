package com.example.careit.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "caregiver_ask")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CaregiverAsk { // 요양 보호사 근무 조건

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;   //요양보호사_id

    @OneToOne(fetch = FetchType.LAZY) // 1:1 매핑
    @JoinColumn(name = "caregiver_id") // unique 추가 - 중복 등록 방지
    private Caregiver careGiver; // careGiver테이블과 1:1 매핑

    @Column(nullable = false)
    private String workArea; // 근무 지역

    private String workDays; // 근무 요일

    private String workHours; // 근무 시간

    private Integer desiredSalary; // 희망 급여

    private String personalityKeywords; // 성격 키워드

    private String strongService; // 자신있는 서비스
}

