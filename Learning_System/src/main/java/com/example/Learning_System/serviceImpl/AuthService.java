package com.example.Learning_System.serviceImpl;

import com.example.Learning_System.dto.LoginRequest;
import com.example.Learning_System.entity.User;
import com.example.Learning_System.repository.UserRepository;
import com.example.Learning_System.security.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtUtil jwtUtil;

    public String login(LoginRequest request) {

        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new RuntimeException("Invalid email or password"));

        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw new RuntimeException("Invalid email or password");
        }

        // JWT generation
        return jwtUtil.generateToken(user.getEmail());
    }


}

