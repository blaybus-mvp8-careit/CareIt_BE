package com.example.careit.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Entity
@Table(name = "admins")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Admin {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "center_id", nullable = false)
    private Center center;

    @Column(nullable = false)
    private String name;

    @Temporal(TemporalType.DATE)
    private Date birthDate;

    private String phone;

    private String address;

    private Boolean ownsBathVehicle;

    @Column(length = 500)
    private String introduction;

    @Temporal(TemporalType.DATE)
    private Date centerStartDate;

    @Temporal(TemporalType.DATE)
    private Date centerEndDate;
}

