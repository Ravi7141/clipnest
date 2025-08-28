package com.example.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.HashSet;
import java.util.Set;
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Document(collection = "users")
public class User {

    @Id
    private String id;

    private String username;

    private String email;

    private String password;

    private String bio;

    private String profileImageUrl;

 @Builder.Default
    private Set<String> followers = new HashSet<>();
 @Builder.Default
    private Set<String> following = new HashSet<>();
}