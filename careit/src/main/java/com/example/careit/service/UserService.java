package com.example.careit.service;

import com.example.careit.dto.UserResponseDto;
import com.example.careit.entity.User;
import com.example.careit.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public Optional<UserResponseDto> getUserByEmail(String email) {
        return userRepository.findByEmail(email).map(UserResponseDto::new);
    }

    public UserResponseDto getUserInfo(User user) {
        return new UserResponseDto(user);  // User 객체 하나만 전달
    }
}

