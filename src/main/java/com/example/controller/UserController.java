package com.example.controller;

import com.example.model.User;
import com.example.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/{id}")
    public Optional<User> getUserById(@PathVariable Long id) {
        return userService.getUserById(id);
    }

    @PutMapping("/{id}")
    public User updateUser(@PathVariable Long id, @RequestBody User updatedUser) {
        return userService.updateUser(id, updatedUser);
    }

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
    }

    @PostMapping("/{id}/follow")
    public void followUser(@PathVariable Long id, @RequestBody Long followId) {
        userService.followUser(id, followId);
    }

    @DeleteMapping("/{id}/unfollow")
    public void unfollowUser(@PathVariable Long id, @RequestBody Long unfollowId) {
        userService.unfollowUser(id, unfollowId);
    }

    @GetMapping("/{id}/followers")
    public List<User> getFollowers(@PathVariable Long id) {
 return userService.getFollowers(id);
    }

    @GetMapping("/{id}/following")
    public List<User> getFollowing(@PathVariable Long id) {
 return userService.getFollowing(id);
    }
}