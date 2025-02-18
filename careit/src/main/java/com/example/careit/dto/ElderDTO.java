package com.example.careit.dto;


import com.example.careit.entity.Admin;
import com.example.careit.entity.Elder;
import com.example.careit.entity.Gender;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Date;

@Getter
@NoArgsConstructor
public class ElderDTO {

    private Long id;
    @NotBlank
    private String name;
    @NotNull
    private Date birthDate;
    @NotNull
    private Gender gender;
    @NotBlank
    private String careGrade; // 장기 요양 등급
    @NotBlank
    private String address; // 주소
    @NotBlank
    private String careDate; // 돌봄 시작 날짜
    @NotBlank
    private String careDays; // 돌봄 요일
    @NotBlank
    private String careStartTime; // 돌봄 시작 시간
    @NotBlank
    private String careEndTime; // 돌봄 종료 시간
    @NotBlank
    private String serviceItems; // 케어 항목
    @NotNull
    private Boolean dementiaSymptoms; // 치매 여부
    @NotBlank
    private String dementia; // 치매 증상
    private String housemate; //동거인 여부
    private String diseaseItems; // 질병 항목
    private String guardianContact; // 보호자 연락처
    private String precautions; // 유의 사항
    private String photo; // 어르신 프로필 사진


    @Builder
    public ElderDTO(Long id, String name, Date birthDate, Gender gender, String careGrade, String address,
                    String careDate, String careDays, String careStartTime, String careEndTime, String serviceItems,
                    Boolean dementiaSymptoms,String dementia, String housemate, String diseaseItems,
                    String guardianContact, String precautions, String photo ) {
        this.id = id;
        this.name = name;
        this.birthDate = birthDate;
        this.gender = gender;
        this.careGrade = careGrade;
        this.address = address;
        this.careDate = careDate;
        this.careDays = careDays;
        this.careStartTime = careStartTime;
        this.careEndTime = careEndTime;
        this.serviceItems = serviceItems;
        this.dementiaSymptoms = dementiaSymptoms;
        this.dementia = dementia;
        this.housemate = housemate;
        this.diseaseItems = diseaseItems;
        this.guardianContact = guardianContact;
        this.precautions = precautions;
        this.photo = photo;
    }

    // DTO -> Entity 변환
    public Elder toEntity(Admin admin) {
        return Elder.builder()
                .admin(admin)
                .name(name)
                .birthDate(birthDate)
                .gender(gender)
                .careGrade(careGrade)
                .address(address)
                .careDate(careDate)
                .careDays(careDays)
                .careStartTime(careStartTime)
                .careEndTime(careEndTime)
                .serviceItems(serviceItems)
                .dementiaSymptoms(dementiaSymptoms)
                .dementia(dementia)
                .housemate(housemate)
                .diseaseItems(diseaseItems)
                .guardianContact(guardianContact)
                .precautions(precautions)
                .photo(photo)
                .build();
    }

    // Entity -> DTO 변환
    public static ElderDTO fromEntity(Elder elder) {
        return ElderDTO.builder()
                .id(elder.getId())
                .name(elder.getName())
                .birthDate(elder.getBirthDate())
                .gender(elder.getGender())
                .careGrade(elder.getCareGrade())
                .address(elder.getAddress())
                .careDate(elder.getCareDate())
                .careDays(elder.getCareDays())
                .careStartTime(elder.getCareStartTime())
                .careEndTime(elder.getCareEndTime())
                .serviceItems(elder.getServiceItems())
                .dementiaSymptoms(elder.getDementiaSymptoms())
                .dementia(elder.getDementia())
                .housemate(elder.getHousemate())
                .diseaseItems(elder.getDiseaseItems())
                .guardianContact(elder.getGuardianContact())
                .precautions(elder.getPrecautions())
                .photo(elder.getPhoto())
                .build();
    }

    // Entity 업데이트
    public void updateEntity(Elder elder) {
        elder.setName(name);
        elder.setBirthDate(birthDate);
        elder.setGender(gender);
        elder.setAddress(address);
        elder.setCareDate(careDate);
        elder.setCareDays(careDays);
        elder.setCareStartTime(careStartTime);
        elder.setCareEndTime(careEndTime);
        elder.setServiceItems(serviceItems);
        elder.setDementiaSymptoms(dementiaSymptoms);
        elder.setDementia(dementia);
        elder.setHousemate(housemate);
        elder.setDiseaseItems(diseaseItems);
        elder.setGuardianContact(guardianContact);
        elder.setPrecautions(precautions);
        elder.setPhoto(photo);
    }
}
