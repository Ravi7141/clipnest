package com.example.service.impl;

import com.example.model.Comment;
import com.example.model.Pin;
// import com.example.model.User; // Assuming User is not directly embedded in Comment and will be referenced by ID
import com.example.repository.CommentRepository;
import com.example.repository.PinRepository;
// import com.example.repository.UserRepository; // Assuming User is not directly embedded in Comment and will be referenced by ID
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
// private final UserRepository userRepository; // Assuming User is not directly embedded in Comment and will be referenced by ID

    @Autowired
    public CommentServiceImpl(CommentRepository commentRepository, PinRepository pinRepository, UserRepository userRepository) {
        this.commentRepository = commentRepository;
        this.pinRepository = pinRepository;
//        this.userRepository = userRepository; // Assuming User is not directly embedded in Comment and will be referenced by ID
    }

    @Override
    public Comment addComment(String pinId, String userId, String text) {
        Pin pin = pinRepository.findById(String.valueOf(pinId)) // MongoDB IDs are typically Strings
                .orElseThrow(() -> new RuntimeException("Pin not found"));
//        User user = userRepository.findById(userId) // Assuming User is not directly embedded in Comment and will be referenced by ID
//                .orElseThrow(() -> new RuntimeException("User not found"));

        Comment comment = new Comment();
// comment.setText(text);
// comment.setCreatedAt(LocalDateTime.now());
// comment.setPinId(pin.getId()); // Store the Pin ID
// comment.setCreatedByUserId(String.valueOf(userId)); // Store the User ID

        return commentRepository.save(comment);
    }

    @Override
    public List<Comment> getCommentsByPin(String pinId) {
        Pin pin = pinRepository.findById(String.valueOf(pinId)) // MongoDB IDs are typically Strings
                .orElseThrow(() -> new RuntimeException("Pin not found"));
        return null; // Find comments by Pin ID
    }

    @Override
    public Comment updateComment(String commentId, String newText) {
        Comment comment = commentRepository.findById(String.valueOf(commentId)) // MongoDB IDs are typically Strings
                .orElseThrow(() -> new RuntimeException("Comment not found"));

        comment.setText(newText);
        // Optionally update a last edited timestamp
//        comment.setUpdatedAt(LocalDateTime.now());

        return commentRepository.save(comment);
    }

    @Override
    public void deleteComment(String commentId) {
        Comment comment = commentRepository.findById(commentId)
                .orElseThrow(() -> new RuntimeException("Comment not found")); // MongoDB IDs are typically Strings
        commentRepository.delete(comment);
    }
}