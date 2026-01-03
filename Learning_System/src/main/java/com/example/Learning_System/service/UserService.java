package com.example.Learning_System.service;

import com.example.Learning_System.dto.UserRequestDto;
import com.example.Learning_System.dto.UserResponseDto;

public interface UserService {
    UserResponseDto registerUser(UserRequestDto dto);
}
