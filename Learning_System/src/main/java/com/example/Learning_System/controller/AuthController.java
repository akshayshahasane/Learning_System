package com.example.Learning_System.controller;

import com.example.Learning_System.dto.LoginRequest;
import com.example.Learning_System.dto.LoginResponse;
import com.example.Learning_System.entity.Role;
import com.example.Learning_System.entity.User;
import com.example.Learning_System.repository.UserRepository;
import com.example.Learning_System.serviceImpl.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "http://localhost:3000")
public class AuthController {

    @Autowired
    private AuthService authService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;


    @PostMapping("/login")
    public LoginResponse login(@RequestBody LoginRequest request) {

        String token = authService.login(request);

        LoginResponse res = new LoginResponse();
        res.setToken(token);

        return res;
    }

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody User user) {

        // check duplicate email
        if (userRepository.findByEmail(user.getEmail()).isPresent()) {
            return ResponseEntity.badRequest().body("Email already registered");
        }

        // encode password
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        // default role if missing
        if (user.getRole() == null) {
            user.setRole(Role.valueOf("STUDENT"));

        }

        userRepository.save(user);

        return ResponseEntity.ok("User registered successfully");
    }
}

