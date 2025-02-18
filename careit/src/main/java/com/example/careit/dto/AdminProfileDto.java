package com.example.careit.dto;

import jakarta.annotation.Nullable;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AdminProfileDto {
    private Long id;  //필요없는 듯 나중에 삭제하기

    @Nullable
    private Long centerId; // centerID만 전달
    @NotBlank
    private String name;
    @NotBlank
    private String address;
    @NotBlank
    private String phone;
}
