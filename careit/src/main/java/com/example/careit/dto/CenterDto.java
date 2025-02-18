package com.example.careit.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CenterDto {
    private Long id;  // 센터 ID
    private String name;  // 센터 이름
    private String address;  // 센터 주소
}
