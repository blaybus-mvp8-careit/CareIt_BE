package com.example.careit.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Entity
@Table(name = "caregivers")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Caregiver {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column(nullable = false)
    private String name;

    @Temporal(TemporalType.DATE)
    private Date birthDate; // 변동될 수 있음. 피그마에 여쭤봄

    @Enumerated(EnumType.STRING)
    private Gender gender; //       "

    @Column(nullable = false)
    private String phone;

    @Column(nullable = false)
    private String address;

    @Column(nullable = false)
    private String certificateName; // 자격증 이름

    @Column(nullable = false)
    private String certificateNumber; // 자격증 번호

    @Column(nullable = false)
    private Boolean ownsCar; // 차량 소유 여부

    private String majorExperience; // 주요 경력

    private String experienceYears; // 경력 기간

    @Column(length = 500)
    private String introduction; // 한 줄 소개

    @Column(nullable = false)
    private Boolean dementiaTraining; // 치매 교육 여부

}

