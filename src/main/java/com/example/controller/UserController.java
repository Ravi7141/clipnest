package com.example.controller;

import com.example.dto.SingleUser;
import com.example.model.User;
import com.example.dto.UsersFollowers;
import com.example.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    // ✅ Get user by id
    @GetMapping("/{id}")
    public ResponseEntity<SingleUser> getUserById(@PathVariable String id) {
        Optional<SingleUser> user = userService.getUserById(id);
        return user.map(ResponseEntity::ok) // 200 OK
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build()); // 404
    }

    // ✅ Update user (kept as per your request)
    @PutMapping("/{id}")
    public ResponseEntity<String> updateUser(@PathVariable String id, @RequestBody User updatedUser) {
        User user = userService.updateUser(id, updatedUser);
        if (user != null) {
            return ResponseEntity.ok("User_updated"); // 200 OK
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build(); // 404
        }
    }

    // ✅ Delete user
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable String id) {
        boolean deleted = userService.deleteUser(id);
        if (deleted) {
            return ResponseEntity.noContent().build(); // 204 No Content
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build(); // 404
        }
    }

    // ✅ Follow user
    @PostMapping("/{id}/follow")
    public ResponseEntity<String> followUser(@PathVariable String id, @RequestParam String followId) {
        boolean followed = userService.followUser(id, followId);
        if (followed) {
            return ResponseEntity.ok("User followed successfully"); // 200 OK
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User or follow target not found"); // 404
        }
    }

    // ✅ Unfollow user
    @DeleteMapping("/{id}/unfollow")
    public ResponseEntity<String> unfollowUser(@PathVariable String id, @RequestParam String unfollowId) {
        boolean unfollowed = userService.unfollowUser(id, unfollowId);
        if (unfollowed) {
            return ResponseEntity.ok("User unfollowed successfully"); // 200 OK
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User or unfollow target not found"); // 404
        }
    }

    // ✅ Get followers
    @GetMapping("/{id}/followers")
    public ResponseEntity<List<UsersFollowers>> getFollowers(@PathVariable String id) {
        List<UsersFollowers> followers = userService.getFollowers(id);
        if (followers != null) {
            return ResponseEntity.ok(followers); // 200 OK
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build(); // 404
        }
    }

    // ✅ Get following
    @GetMapping("/{id}/following")
    public ResponseEntity<List<UsersFollowers>> getFollowing(@PathVariable String id) {
        List<UsersFollowers> following = userService.getFollowing(id);
        if (following != null) {
            return ResponseEntity.ok(following); // 200 OK
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build(); // 404
        }
    }
}
