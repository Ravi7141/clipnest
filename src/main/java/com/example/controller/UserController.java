package com.example.controller;

import com.example.model.User;
import com.example.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/{id}")
    public String getUserById(@PathVariable Long id) {
        return "Hello world";
    }

    @PutMapping("/{id}")
    public User updateUser(@PathVariable Long id, @RequestBody User updatedUser) {
        return null;
        // TODO: Implement
    }

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable Long id) {
        // TODO: Implement
    }

    @PostMapping("/{id}/follow")
    public void followUser(@PathVariable Long id, @RequestBody Long followId) {
        // TODO: Implement
    }

    @DeleteMapping("/{id}/unfollow")
    public void unfollowUser(@PathVariable Long id, @RequestBody Long unfollowId) {
        // TODO: Implement
    }

    @GetMapping("/{id}/followers")
    public List<User> getFollowers(@PathVariable Long id) {
        return null; // TODO: Implement
    }

    @GetMapping("/{id}/following")
    public List<User> getFollowing(@PathVariable Long id) {
        return null; // TODO: Implement
    }
}