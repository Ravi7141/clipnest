package com.example.controller;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @PostMapping("/register")
    public void register() {
        // Method stub for registration
    }

    @PostMapping("/login")
    public void login() {
        // Method stub for login
    }

    @PostMapping("/logout")
    public void logout() {
        // Method stub for logout
    }
}