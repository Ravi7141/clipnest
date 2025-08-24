package com.example.service;

import com.example.model.Comment;

import java.util.List;

public interface CommentService {
    Comment addComment(Long pinId, Long userId, String text);
    List<Comment> getCommentsByPin(Long pinId);
    Comment updateComment(Long commentId, String newText);
    void deleteComment(Long commentId);
}