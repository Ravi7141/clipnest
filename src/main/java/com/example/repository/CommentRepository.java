package com.example.repository;

import com.example.model.Comment;
import com.example.model.Pin;
import org.springframework.data.mongodb.repository.MongoRepository;
import java.util.List;

public interface CommentRepository extends MongoRepository<Comment, String> {
    List<Comment> findByPinId(String pinId);
}