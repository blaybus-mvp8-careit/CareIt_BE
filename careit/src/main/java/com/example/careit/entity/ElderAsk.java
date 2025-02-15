package com.example.careit.entity;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Table(name = "elder_ask")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class ElderAsk { // 어르신 요구 조건 - 기능명세서 센터의 필수 채용 조건 설정 참고
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // 어르신 기본키
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "elder_id", nullable = false) // 어르신 테이블과 1:1 매핑
    private Elder elder;

    @Enumerated(EnumType.STRING)
    @JoinColumn(nullable = false)
    private CaregiverCount caregiverCount; // 선호 요양사 수 택 1

    private String preferCharacter; // 선호 요양사 성격

    private String preferGender; // 선호 요양사 성별

    @JoinColumn(nullable = false)
    private Integer hourlyWage; // 시급

    @JoinColumn(nullable = false)
    private String subSchedule; // 후순위 일정
    
}
