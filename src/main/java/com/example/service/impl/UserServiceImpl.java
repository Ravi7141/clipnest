package com.example.service.impl;

import com.example.dto.SingleUser;
import com.example.model.User;
import com.example.dto.UsersFollowers;
import com.example.repository.UserRepository;
import com.example.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public Optional<SingleUser> getUserById(Long id) {
        User user = userRepository.findById(id).orElse(null);

        if (user != null) {
            SingleUser dto = new SingleUser();
            dto.setId(user.getId());
            dto.setUsername(user.getUsername());

            // Only IDs to avoid recursion
            dto.setFollowerIds((
                    user.getFollowers().stream()
                            .map(User::getId)
                            .collect(Collectors.toSet())
            ));
            dto.setFollowingIds((
                    user.getFollowing().stream()
                            .map(User::getId)
                            .collect(Collectors.toSet())
            ));

            return Optional.of(dto);
        }

        return Optional.empty();
    }


    @Override
    public User updateUser(Long id, User updatedUser) {
        return userRepository.findById(id)
                .map(user -> {
                    user.setUsername(updatedUser.getUsername());
                    user.setEmail(updatedUser.getEmail());
                    user.setPassword(updatedUser.getPassword());
                    user.setBio(updatedUser.getBio());
                    user.setProfileImageUrl(updatedUser.getProfileImageUrl());
                    return userRepository.save(user);
                })
                .orElse(null); // Controller will handle null -> 404
    }

    @Override
    public boolean deleteUser(Long id) {
        Optional<User> userOpt = userRepository.findById(id);
        if (userOpt.isPresent()) {
            User user = userOpt.get();

            // Remove from followers' following list
            for (User follower : new HashSet<>(user.getFollowers())) {
                follower.getFollowing().remove(user);
            }

            // Remove from following's followers list
            for (User following : new HashSet<>(user.getFollowing())) {
                following.getFollowers().remove(user);
            }

            user.getFollowers().clear();
            user.getFollowing().clear();

            userRepository.delete(user); // Now safe
            return true;
        }
        return false;
    }

    @Override
    public boolean followUser(Long userId, Long followId) {
        Optional<User> userOpt = userRepository.findById(userId);
        Optional<User> followOpt = userRepository.findById(followId);

        if (userOpt.isPresent() && followOpt.isPresent()) {
            User user = userOpt.get();
            User follow = followOpt.get();

            user.getFollowing().add(follow);
            follow.getFollowers().add(user);

            userRepository.save(user);
            userRepository.save(follow);
            return true;
        }
        return false;
    }

    @Override
    public boolean unfollowUser(Long userId, Long unfollowId) {
        Optional<User> userOpt = userRepository.findById(userId);
        Optional<User> unfollowOpt = userRepository.findById(unfollowId);

        if (userOpt.isPresent() && unfollowOpt.isPresent()) {
            User user = userOpt.get();
            User unfollow = unfollowOpt.get();

            user.getFollowing().remove(unfollow);
            unfollow.getFollowers().remove(user);

            userRepository.save(user);
            userRepository.save(unfollow);
            return true;
        }
        return false;
    }

    @Override
    public List<UsersFollowers> getFollowers(Long userId) {
        User user = userRepository.findById(userId).orElse(null);
        if (user == null) return null;
        List<UsersFollowers> followersList = new ArrayList<>();
        for (User follower : user.getFollowers()) {
            followersList.add(new UsersFollowers(follower.getId(), follower.getUsername()));
        }
        return followersList;
    }

    @Override
    public List<UsersFollowers> getFollowing(Long userId) {
        User user = userRepository.findById(userId).orElse(null);
        if (user == null) return null;
        List<UsersFollowers> followersList = new ArrayList<>();
        for (User follower : user.getFollowing()) {
            followersList.add(new UsersFollowers(follower.getId(), follower.getUsername()));
        }
        return followersList;
    }
}
