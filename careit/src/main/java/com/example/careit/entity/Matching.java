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
public class Matching { // 확정된 매칭에 대한 엔티티
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
    private MatchingStatus status; // 매칭 상태

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private ProgressStatus progress; // 매칭 진행 상태

    @Temporal(TemporalType.DATE)
    private Date rejectedAt; // 매칭 거절 날짜

    @Temporal(TemporalType.DATE)
    private Date acceptedAt; // 매칭 수락 날짜

    @Column(length = 500)
    private String rejectionReason; // 매칭 거절 사유 - pm분께서 추가안하신다고 하셨던거 같음(필요시 나중에 삭제)

    @Column(length = 255)
    private String workStartDate; // 확정된 근무 시작 날짜

    @Column(length = 255)
    private String workDay; // 확정된 근무 요일

    @Column(length = 255)
    private String workTime; // 확정된 근무 시간대

    @Column(length = 255)
    private String careList; // 확정된 서비스 목록

    private Integer hourlyWage; // 확정된 시급
}

enum MatchingStatus {
    PENDING, IN_PROGRESS, COMPLETED
}

enum ProgressStatus {
    WAITING, ACCEPTED, NEGOTIATION, REJECTED, DONE
}

