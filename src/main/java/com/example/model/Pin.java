package com.example.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.example.model.User;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "pins")
@Data
@AllArgsConstructor
@Builder
public class Pin {
    @Id
    private String id;
    private String title;
    private String description;
    private String imageUrl;

    @DBRef
    private User createdBy; // Assuming User will also be a Document

    private Set<String> boardIds = new HashSet<>(); // Store Board IDs

    @DBRef
    private List<Comment> comments = new ArrayList<>(); // Using DBRef for simplicity, consider embedding or ID list for performance

    private Set<String> likedByUsers = new HashSet<>(); // Store User IDs of likers
}