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
    private Date birthDate;

    private String phone;

    private String address;

    private String certificateName;

    private String certificateNumber;

    private Boolean ownsCar;

    private String majorExperience;

    private Integer experienceYears;

    @Column(length = 500)
    private String introduction;

    private String workArea;

    private String workDays;

    private String workHours;

    private Integer desiredSalary;

    private Boolean dementiaTraining;

    private String personalityKeywords;

    private String strongService;
}

