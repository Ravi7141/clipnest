package com.example.controller;

import com.example.model.Comment;
import com.example.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/comments")
public class CommentController {

    @Autowired
    private CommentService commentService;

    @PostMapping("/pin/{pinId}/user/{userId}")
    public Comment addComment(@PathVariable Long pinId, @PathVariable Long userId, @RequestBody String text) {
        // Method body will be implemented later
        return null;
    }

    @GetMapping("/pin/{pinId}")
    public List<Comment> getCommentsByPin(@PathVariable Long pinId) {
        // Method body will be implemented later
        return null;
    }

    @PutMapping("/{id}")
    public Comment updateComment(@PathVariable Long id, @RequestBody String newText) {
        // Method body will be implemented later
        return null;
    }

    @DeleteMapping("/{id}")
    public void deleteComment(@PathVariable Long id) {
        // Method body will be implemented later
    }
}