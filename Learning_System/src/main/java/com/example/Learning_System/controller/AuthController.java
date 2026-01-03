package com.example.Learning_System.controller;

import com.example.Learning_System.dto.LoginRequest;
import com.example.Learning_System.dto.LoginResponse;
import com.example.Learning_System.serviceImpl.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping("/login")
    public LoginResponse login(@RequestBody LoginRequest request) {

        String token = authService.login(request);

        LoginResponse res = new LoginResponse();
        res.setToken(token);

        return res;
    }
}

