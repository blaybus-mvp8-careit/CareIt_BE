package com.example.careit.dto;

import com.example.careit.entity.Role;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SignupRequestDto {
    @NotBlank
    private String email;

    @NotBlank
    private String password;

    @NotNull
    private Role role; // ADMIN, CAREGIVER

    private MultipartFile photo; // String → MultipartFile 로 변경
}

