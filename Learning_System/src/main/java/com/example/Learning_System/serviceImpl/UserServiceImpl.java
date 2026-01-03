package com.example.Learning_System.serviceImpl;

import com.example.Learning_System.dto.UserRequestDto;
import com.example.Learning_System.dto.UserResponseDto;
import com.example.Learning_System.entity.User;
import com.example.Learning_System.repository.UserRepository;
import com.example.Learning_System.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;


    @Override
    public UserResponseDto registerUser(UserRequestDto dto) {

        User user = new User();
        user.setName(dto.getName());
        user.setEmail(dto.getEmail());
        user.setPassword(passwordEncoder.encode(dto.getPassword()));
        user.setRole(dto.getRole());

        User saved = userRepository.save(user);

        UserResponseDto res = new UserResponseDto();
        res.setId(saved.getId());
        res.setName(saved.getName());
        res.setEmail(saved.getEmail());
        res.setRole(saved.getRole());

        return res;
    }
}

