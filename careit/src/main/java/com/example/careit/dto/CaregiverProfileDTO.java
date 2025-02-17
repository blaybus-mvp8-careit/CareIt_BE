package com.example.careit.dto;


import com.example.careit.entity.Caregiver;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CaregiverProfileDTO {

    @NotBlank
    private String name;

    @NotBlank
    private String phone;

    @NotBlank
    private String certificateName;

    @NotBlank
    private String certificateNumber;

    @NotBlank
    private String address;

    @NotNull
    private Boolean ownsCar;

    @NotNull
    private Boolean dementiaTraining;

    private String introduction;
    private String majorExperience;
    private String experienceYears;


    public static CaregiverProfileDTO from(Caregiver entity) {
        return CaregiverProfileDTO.builder()
                .name(entity.getName())
                .phone(entity.getPhone())
                .certificateName(entity.getCertificateName())
                .certificateNumber(entity.getCertificateNumber())
                .address(entity.getAddress())
                .ownsCar(entity.getOwnsCar())
                .dementiaTraining(entity.getDementiaTraining())
                .introduction(entity.getIntroduction())
                .majorExperience(entity.getMajorExperience())
                .experienceYears(entity.getExperienceYears())
                .build();
    }

    public void updateEntity(Caregiver entity) {
        entity.setName(this.name);
        entity.setPhone(this.phone);
        entity.setCertificateName(this.certificateName);
        entity.setCertificateNumber(this.certificateNumber);
        entity.setAddress(this.address);
        entity.setOwnsCar(this.ownsCar);
        entity.setDementiaTraining(this.dementiaTraining);
        entity.setIntroduction(this.introduction);
        entity.setMajorExperience(this.majorExperience);
        entity.setExperienceYears(this.experienceYears);
    }

}
