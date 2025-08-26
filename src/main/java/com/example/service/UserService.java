package com.example.service;

import com.example.model.User;
import java.util.Optional;


import java.util.List;

public interface UserService {
    Optional<User> getUserById(Long id);
    User updateUser(Long id, User updatedUser);
    void deleteUser(Long id);
    void followUser(Long userId, Long followId);
    void unfollowUser(Long userId, Long unfollowId);
    List<User> getFollowers(Long userId);
    List<User> getFollowing(Long userId);
}