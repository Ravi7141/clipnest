package com.example.dto;

public class UsersFollowers {
    private String id;
    private String username; // or email, displayName, etc.

    public UsersFollowers(String id, String username) {
        this.id = id;
        this.username = username;
    }

    public String getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }
}
