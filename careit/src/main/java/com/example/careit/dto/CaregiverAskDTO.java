package com.example.careit.dto;


import com.example.careit.entity.CaregiverAsk;
import jakarta.transaction.Transactional;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CaregiverAskDTO { // 근무 조건

    @NotBlank
    private String workArea; // 근무 지역 - 리스트?
    @NotBlank
    private String workDays; // 근무 요일
    @NotBlank
    private String workHours; // 근무 시간
    @NotNull
    private Integer desiredSalary; // 희망 급여
    @NotBlank
    private String personalityKeywords; // 성격 키워드
    @NotBlank
    private String strongService; // 자신있는 서비스

    public static CaregiverAskDTO from(CaregiverAsk entity) {
        return CaregiverAskDTO.builder()
                .workArea(entity.getWorkArea())
                .workDays(entity.getWorkDays())
                .workHours(entity.getWorkHours())
                .desiredSalary(entity.getDesiredSalary())
                .personalityKeywords(entity.getPersonalityKeywords())
                .strongService(entity.getStrongService())
                .build();
    }

    public CaregiverAsk toEntity() {
        return CaregiverAsk.builder()
                .workArea(this.workArea)
                .workDays(this.workDays)
                .workHours(this.workHours)
                .desiredSalary(this.desiredSalary)
                .personalityKeywords(this.personalityKeywords)
                .strongService(this.strongService)
                .build();
    }

}
