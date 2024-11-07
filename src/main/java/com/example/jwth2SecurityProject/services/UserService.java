package com.example.jwth2SecurityProject.services;


import com.example.jwth2SecurityProject.config.JwtTokenProvider;
import com.example.jwth2SecurityProject.dtos.LoginRequest;
import com.example.jwth2SecurityProject.dtos.LoginResponse;
import com.example.jwth2SecurityProject.dtos.RegisterRequest;
import com.example.jwth2SecurityProject.exceptions.UserNotFoundException;
import com.example.jwth2SecurityProject.models.User;
import com.example.jwth2SecurityProject.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    public void register(RegisterRequest request) {
        User user = new User();
        user.setUsername(request.getUsername());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setRole(request.getRole());
        userRepository.save(user);
    }


    public LoginResponse login(LoginRequest request) {
        List<User> users = userRepository.findAll();

        User user = users.stream()
                .filter(u -> u.getUsername().equals(request.getUsername())) // Check if username matches
                .findFirst() // Get the first matching user
                .orElseThrow(() -> new RuntimeException("Invalid username or password")); // Throw exception if not found
        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw new RuntimeException("Invalid username or password"); // Throw exception if password doesn't match
        }

        String token = jwtTokenProvider.generateToken(user);
        return new LoginResponse(user.getUsername(), user.getRole(), user.getId(), token);
    }


}

