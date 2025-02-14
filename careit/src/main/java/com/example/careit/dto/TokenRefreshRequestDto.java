package com.example.careit.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Schema(description = "토큰 재발급 요청 DTO")
public class TokenRefreshRequestDto {

    @Schema(description = "리프레시 토큰", example = "eyJhbGciOiJIUzI1...")
    private String refreshToken;
}
