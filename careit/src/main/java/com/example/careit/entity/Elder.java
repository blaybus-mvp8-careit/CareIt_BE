package com.example.careit.entity;

import jakarta.persistence.*;
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
public class Elder {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "admin_id", nullable = false)
    private Admin admin;

    @Column(nullable = false)
    private String name;

    @Temporal(TemporalType.DATE)
    private Date birthDate;

    @Column(nullable = false)
    private String gender;

    private String address;

    private String careGrade;

    private Integer hourlyWage;

    private String careDays;

    private String careStartTime;

    private String careEndTime;

    private String serviceItems;

    private String diseaseItems;

    private Boolean hasCohabitant;

    @Column(length = 500)
    private String precautions;

    private Boolean dementiaSymptoms;
}

