package com.example.jwth2SecurityProject.controllers;

import com.example.jwth2SecurityProject.dtos.LoginRequest;
import com.example.jwth2SecurityProject.dtos.LoginResponse;
import com.example.jwth2SecurityProject.dtos.RegisterRequest;
import com.example.jwth2SecurityProject.services.UserService;
import com.example.jwth2SecurityProject.utils.LoggerUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody RegisterRequest request) {
        try {
            userService.register(request);
            LoggerUtil.getLogger().info(request.getUsername() + " created account successfully.");
            return ResponseEntity.ok("User registered successfully");
        }catch (Exception e) {
            LoggerUtil.getLogger().error(e);
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest request) {
        try {
            LoginResponse response=userService.login((request));
            if(response!=null) {
                LoggerUtil.getLogger().info(request.getUsername() + " login done!.");
                return ResponseEntity.ok(response);
            }
            LoggerUtil.getLogger().warn(request.getUsername() + " login failed.Try Again!");
            return new ResponseEntity<>(null,HttpStatus.NOT_FOUND);
        }catch (Exception e) {
            LoggerUtil.getLogger().error(e);
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
