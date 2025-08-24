package com.example.service.impl;
import com.example.model.Comment;
import com.example.model.Pin;
import com.example.model.User;
import com.example.repository.CommentRepository;
import com.example.repository.PinRepository;
import com.example.repository.UserRepository;
import com.example.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class CommentServiceImpl implements CommentService {

    private final CommentRepository commentRepository;
    private final PinRepository pinRepository;
    private final UserRepository userRepository;

    @Autowired
    public CommentServiceImpl(CommentRepository commentRepository, PinRepository pinRepository, UserRepository userRepository) {
        this.commentRepository = commentRepository;
        this.pinRepository = pinRepository;
        this.userRepository = userRepository;
    }

    @Override
    public Comment addComment(Long pinId, Long userId, String text) {
        Pin pin = pinRepository.findById(pinId)
                .orElseThrow(() -> new RuntimeException("Pin not found"));
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        Comment comment = new Comment();
        comment.setText(text);
        comment.setCreatedAt(LocalDateTime.now());
        comment.setPin(pin);
        comment.setCreatedBy(user);

        return commentRepository.save(comment);
    }

    @Override
    public List<Comment> getCommentsByPin(Long pinId) {
        Pin pin = pinRepository.findById(pinId)
                .orElseThrow(() -> new RuntimeException("Pin not found"));
        return commentRepository.findByPin(pin);
    }

    @Override
    public Comment updateComment(Long commentId, String newText) {
        Comment comment = commentRepository.findById(commentId)
                .orElseThrow(() -> new RuntimeException("Comment not found"));

        comment.setText(newText);
        // Optionally update a last edited timestamp
        // comment.setUpdatedAt(LocalDateTime.now());

        return commentRepository.save(comment);
    }

    @Override
    public void deleteComment(Long commentId) {
        Comment comment = commentRepository.findById(commentId)
                .orElseThrow(() -> new RuntimeException("Comment not found"));
        commentRepository.delete(comment);
    }
}