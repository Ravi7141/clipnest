package com.example.service;

import com.example.model.User;

public interface AuthService {
    User register(User user);
    User login(String email, String password);
//    void logout();
}
