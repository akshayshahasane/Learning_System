package com.example.Learning_System.dto;

import lombok.Data;

@Data
public class ChangePasswordDto {

    private String oldPassword;
    private String newPassword;
}
