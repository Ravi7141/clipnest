package com.example.service;

import com.example.dto.SingleUser;
import com.example.model.User;
import com.example.dto.UsersFollowers;

import java.util.Optional;
import java.util.List;

public interface UserService {
    Optional<SingleUser> getUserById(String id);
    User updateUser(String id, User updatedUser);
    boolean deleteUser(String id);           // changed from void → boolean
    boolean followUser(String userId, String followId);     // changed from void → boolean
    boolean unfollowUser(String userId, String unfollowId); // changed from void → boolean
    List<UsersFollowers> getFollowers(String userId);
    List<UsersFollowers> getFollowing(String userId);
}
