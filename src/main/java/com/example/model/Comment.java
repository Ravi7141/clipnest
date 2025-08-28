package com.example.model;

import lombok.AllArgsConstructor;
import com.example.model.Pin;
import lombok.Builder;
import lombok.Data;

import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

import com.example.model.User;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DBRef;

@Document(collection = "comments")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Comment {
    @Id
    private String id;
    private String text;
    private LocalDateTime createdAt;
}
