package com.workintech.twiterdemo.controller;

import com.workintech.twiterdemo.dto.RegisterRequest;
import com.workintech.twiterdemo.dto.UserResponse;
import com.workintech.twiterdemo.service.UserService;
import lombok.RequiredArgsConstructor;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final UserService userService;

    @PostMapping("/register")
    public UserResponse register(@Valid @RequestBody RegisterRequest registerRequest) {
        return userService.register(registerRequest);
    }

    // Login işlemi Spring Security tarafından otomatik yönetilecek (Basic Auth),

    @GetMapping("/login")
    public String login() {
        return "Giriş başarılı!";
    }
}