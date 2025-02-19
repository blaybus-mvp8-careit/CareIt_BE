package com.example.careit.dto;

import com.example.careit.entity.Center;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CenterDto {
    private Long id;  // 센터 ID

    @NotBlank
    private String name;  // 센터 이름

    @NotBlank
    private String address;  // 센터 주소

    @NotBlank
    private String phone; //센터 연락처

    @NotNull
    private Boolean hasBathVehicle; //목욕 차량 소유 여부

    private Center.CenterGrade centerGrade; //센터 등급(선택)

    private String operatingPeriod; // 운영 기간 (선택)

    private String welfareBenefits; // 복리후생 (선택)

    private String centerPhoto; // 센터 사진 (선택)

    private String introduction; // 센터 한 줄 소개 (선택)

}
