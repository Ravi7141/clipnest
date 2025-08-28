package com.example.repository;

import com.example.model.Pin;
import com.example.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface PinRepository extends MongoRepository<Pin, String> {
    List<Pin> findByCreatedBy(User user);
    List<Pin> findByTitleContainingIgnoreCaseOrDescriptionContainingIgnoreCase(String title, String description);

}