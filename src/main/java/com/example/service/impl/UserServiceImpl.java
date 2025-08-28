package com.example.service.impl;

import com.example.dto.SingleUser;
import com.example.model.User;
import com.example.dto.UsersFollowers;
import com.example.repository.UserRepository;
import com.example.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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
    public Optional<SingleUser> getUserById(String id) {
        User user = userRepository.findById(id).orElse(null);

        if (user != null) {
            SingleUser dto = new SingleUser();
            dto.setId(user.getId());
            dto.setUsername(user.getUsername());

            // Only IDs to avoid recursion
            dto.setFollowerIds((
                    user.getFollowers().stream()
                            .map(String::valueOf)
                            .collect(Collectors.toSet())
            ));
            dto.setFollowingIds((
                    user.getFollowing().stream()
                            .map(String::valueOf)
                            .collect(Collectors.toSet())
            ));

            return Optional.of(dto);
        }

        return Optional.empty();
    }


    @Override
    public User updateUser(String id, User updatedUser) {
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
    public boolean deleteUser(String id) {
        Optional<User> userOpt = userRepository.findById(id);
        if (userOpt.isPresent()) {
            User user = userOpt.get();

            // Remove this user's ID from the followers list of users they were following
            user.getFollowing().forEach(followingId -> {
                userRepository.findById(followingId).ifPresent(followingUser -> {
                    followingUser.getFollowers().remove(user.getId());
                    userRepository.save(followingUser);
                });
            });

            // Remove this user's ID from the following list of users who were following them
            user.getFollowers().forEach(followerId -> {
                userRepository.findById(followerId).ifPresent(follower -> {
                    follower.getFollowing().remove(user.getId());
                    userRepository.save(follower);
                });
            });

            userRepository.delete(user); // Now safe
            return true;
        }
        return false;
    }

    @Override
    public boolean followUser(String userId, String followId) {
        Optional<User> userOpt = userRepository.findById(userId);
        Optional<User> followOpt = userRepository.findById(followId);

        if (userOpt.isPresent() && followOpt.isPresent()) {
            User user = userOpt.get();
            User follow = followOpt.get();

            user.getFollowing().add(follow.getId());
            follow.getFollowers().add(user.getId());

            userRepository.save(user); // Save both users to update their lists
            userRepository.save(follow); // Save both users to update their lists
            return true;
        }
        return false;
    }

    @Override
    public boolean unfollowUser(String userId, String unfollowId) {
        Optional<User> userOpt = userRepository.findById(userId);
        Optional<User> unfollowOpt = userRepository.findById(unfollowId);

        if (userOpt.isPresent() && unfollowOpt.isPresent()) {
            User user = userOpt.get();
            User unfollow = unfollowOpt.get();

            user.getFollowing().remove(unfollow.getId());
            unfollow.getFollowers().remove(user.getId());

            userRepository.save(user); // Save both users to update their lists
            userRepository.save(unfollow); // Save both users to update their lists
            return true;
        }
        return false;
    }

    @Override
    public List<UsersFollowers> getFollowers(String userId) {
        User user = userRepository.findById(userId).orElse(null);
        if (user == null) return null;

        List<UsersFollowers> followersList = new ArrayList<>();
        user.getFollowers().forEach(followerId -> {
            userRepository.findById(followerId).ifPresent(follower -> {
                followersList.add(new UsersFollowers(follower.getId(), follower.getUsername()));
            });
        });

        return followersList;
    }

    @Override
    public List<UsersFollowers> getFollowing(String userId) {
        User user = userRepository.findById(userId).orElse(null);
        if (user == null) return null;

        List<UsersFollowers> followersList = new ArrayList<>();
        user.getFollowing().forEach(followingId -> {
            userRepository.findById(followingId).ifPresent(followingUser -> {
                followersList.add(new UsersFollowers(followingUser.getId(), followingUser.getUsername()));
            });
        });

        return followersList;
    }
}
