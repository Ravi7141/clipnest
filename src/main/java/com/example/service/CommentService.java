package com.example.service;

import com.example.model.Comment;

import java.util.List;

public interface CommentService {
    Comment addComment(String pinId, String userId, String text);
    List<Comment> getCommentsByPin(String pinId);
    Comment updateComment(String commentId, String newText);
    void deleteComment(String commentId);
}