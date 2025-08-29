package com.example.controller;

import com.example.model.Comment;
import com.example.service.CommentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@RestController
@RequestMapping("/api/comments")
public class CommentController {


    private CommentService commentService;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @PostMapping("/pin/{pinId}/user/{userId}")
    public ResponseEntity<Comment> addComment(@PathVariable String pinId, @PathVariable String userId, @RequestBody String text) {
        Comment comment = commentService.addComment(pinId, userId, text);
        return new ResponseEntity<>(comment, HttpStatus.CREATED);
    }

    @GetMapping("/pin/{pinId}")
    public ResponseEntity<List<Comment>> getCommentsByPin(@PathVariable String pinId) {
        List<Comment> comments = commentService.getCommentsByPin(pinId);
        if (comments.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(comments, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Comment> updateComment(@PathVariable String id, @RequestBody String newText) {
        Comment updatedComment = commentService.updateComment(id, newText);
        return new ResponseEntity<>(updatedComment, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteComment(@PathVariable String id) {
        commentService.deleteComment(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}