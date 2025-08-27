package com.example.service;

import com.example.dto.SingleUser;
import com.example.model.User;
import com.example.dto.UsersFollowers;

import java.util.Optional;
import java.util.List;

public interface UserService {
    Optional<SingleUser> getUserById(Long id);
    User updateUser(Long id, User updatedUser);
    boolean deleteUser(Long id);           // changed from void → boolean
    boolean followUser(Long userId, Long followId);     // changed from void → boolean
    boolean unfollowUser(Long userId, Long unfollowId); // changed from void → boolean
    List<UsersFollowers> getFollowers(Long userId);
    List<UsersFollowers> getFollowing(Long userId);
}
