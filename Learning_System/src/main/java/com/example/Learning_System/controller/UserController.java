package com.example.Learning_System.controller;

import com.example.Learning_System.dto.ChangePasswordDto;
import com.example.Learning_System.dto.UpdateProfileDto;
import com.example.Learning_System.dto.UserRequestDto;
import com.example.Learning_System.dto.UserResponseDto;
import com.example.Learning_System.entity.User;
import com.example.Learning_System.repository.UserRepository;
import com.example.Learning_System.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;


    @PostMapping("/register")
    public UserResponseDto register(@Valid @RequestBody UserRequestDto dto) {
        return userService.registerUser(dto);
    }

    @PostMapping("/{userId}/change-password")
    public String changePassword(@PathVariable Long userId,
                                 @RequestBody ChangePasswordDto dto) {

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        boolean matches = passwordEncoder.matches(dto.getOldPassword(), user.getPassword());

        if (!matches) {
            throw new RuntimeException("Old password incorrect");
        }

        user.setPassword(passwordEncoder.encode(dto.getNewPassword()));
        userRepository.save(user);

        return "Password changed successfully";
    }

    @PutMapping("/{userId}/update-profile")
    public User updateProfile(@PathVariable Long userId,
                              @RequestBody UpdateProfileDto dto) {

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        user.setName(dto.getName());
        user.setEmail(dto.getEmail());

        return userRepository.save(user);
    }


}

