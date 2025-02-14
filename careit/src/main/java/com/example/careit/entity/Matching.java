package com.example.careit.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Entity
@Table(name = "matchings")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Matching {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "elder_id", nullable = false)
    private Elder elder;

    @ManyToOne
    @JoinColumn(name = "caregiver_id", nullable = false)
    private Caregiver caregiver;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private MatchingStatus status;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private ProgressStatus progress;

    @Temporal(TemporalType.DATE)
    private Date rejectedAt;

    @Temporal(TemporalType.DATE)
    private Date acceptedAt;

    @Column(length = 500)
    private String rejectionReason;
}

enum MatchingStatus {
    PENDING, IN_PROGRESS, COMPLETED
}

enum ProgressStatus {
    WAITING, ACCEPTED, NEGOTIATION, REJECTED, DONE
}

