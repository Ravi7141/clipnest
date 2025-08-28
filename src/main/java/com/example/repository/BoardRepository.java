package com.example.repository;

import com.example.model.Board;
import com.example.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface BoardRepository extends MongoRepository<Board, String> {
    List<Board> findByCreatedBy(String userId);
}