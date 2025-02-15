package com.example.careit.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Entity
@Table(name = "elders")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Elder { // 어르신 기본 정보 등록을 위한 엔티티 - 기능 명세서 센터의 어르신 정보 등록 참고
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "admin_id", nullable = false)
    private Admin admin; // 관리자랑 N(어르신):1 매핑

    @Column(nullable = false)
    private String name; // 어르신 성함

    @Temporal(TemporalType.DATE)
    @JoinColumn(nullable = false)
    private Date birthDate;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    @JoinColumn(nullable = false)
    private Gender gender;

    @JoinColumn(nullable = false)
    private String address;

    @JoinColumn(nullable = false)
    private String careGrade; // 장기 요양 등급

    @JoinColumn(nullable = false)
    private String careDays; // 케어 요일

    @JoinColumn(nullable = false)
    private String careStartTime; // 케어 시작 시간

    @JoinColumn(nullable = false)
    private String careEndTime; // 케어 종료 시간

    @JoinColumn(nullable = false)
    private String serviceItems; // 서비스 항목

    private String diseaseItems; // 질병 사항

    @JoinColumn(nullable = false)
    private Boolean hasCohabitant; // 치매 여부

    @Column(length = 500)
    private String precautions; // 유의 사항

    private Boolean dementiaSymptoms; // 치매 여부

    private String dementia; // 치매 증상

    private String photo; // 어르신 사진

    private String housemate; //동거인 여부


}

