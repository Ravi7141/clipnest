package com.example.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.HashSet;
import java.util.Set;

@Document(collection = "boards")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Board {
    @Id
    private String id;
    private String name;
    private String description;

//    @ManyToOne
//    @JoinColumn(name = "user_id", nullable = false)
    private String createdBy;

    @DBRef
    private Set<Pin> pins = new HashSet<>();
}