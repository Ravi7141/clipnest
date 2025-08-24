package com.example.repository;

import com.example.model.Pin;
import com.example.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PinRepository extends JpaRepository<Pin, Long> {
    List<Pin> findByCreatedBy(User user);
    List<Pin> findByTitleContaining(String key);
}