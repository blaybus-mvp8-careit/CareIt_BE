package com.example.careit.dto;

import com.example.careit.entity.User;
import com.example.careit.entity.Role;
import lombok.Getter;

@Getter
public class UserResponseDto {
    private Long id;
    private String email;
    private Role role;
    private String photoUrl;

    public UserResponseDto(User user) {
        this.id = user.getId();
        this.email = user.getEmail();
        this.role = user.getRole();
        this.photoUrl = user.getPhotoUrl();
    }
}
