package com.example.Learning_System.dto;

import com.example.Learning_System.entity.Role;
import lombok.Data;

@Data
public class UserResponseDto {
    private Long id;
    private String name;
    private String email;
    private Role role;
}

