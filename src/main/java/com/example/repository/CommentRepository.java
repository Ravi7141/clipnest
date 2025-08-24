package com.example.repository;

import com.example.model.Comment;
import com.example.model.Pin;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    List<Comment> findByPin(Pin pin);
}