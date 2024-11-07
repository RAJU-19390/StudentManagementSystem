package com.example.jwth2SecurityProject.dtos;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class LoginResponse {
    private String username;
    private String role;
    private Long userId;
    private String token;
}

