package com.example.dto;

public class UsersFollowers {
    private Long id;
    private String username; // or email, displayName, etc.

    public UsersFollowers(Long id, String username) {
        this.id = id;
        this.username = username;
    }

    public Long getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }
}
