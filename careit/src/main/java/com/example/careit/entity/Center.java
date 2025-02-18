package com.example.careit.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Entity
@Table(name = "centers")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Center {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "admin_id") //, nullable = false
    private Admin registeredBy; // 등록 관리자 아이디

    @Column(nullable = false)
    private String name; // 센터 이름

    @Column(length = 255,nullable = false)
    private String address;

    @Column(nullable = false)
    private String phone;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(nullable = false, updatable = false)
    private Date registeredAt; // 센터 등록 일자

    @Column(nullable = false)
    private Boolean hasBathVehicle; // 목욕 차량 소유 여부

    @Enumerated(EnumType.STRING)
    private CenterGrade centerGrade; // 센터 등급

    private String operatingPeriod; // 운영 기간

    private String welfareBenefits; // 복리후생

    private String centerPhoto; // 센터 사진

    private String introduction;// 센터 한 줄 소개

    public enum CenterGrade {
        GRADE_A, GRADE_B, GRADE_C, GRADE_D, UNKNOWN // 필요에 따라 등급 추가
    }
}

