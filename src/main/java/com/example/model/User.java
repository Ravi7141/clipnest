package com.example.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String username;

    @Column(unique = true, nullable = false)
    private String email;

    @Column(nullable = false)
    private String password;

    @Column(columnDefinition = "TEXT")
    private String bio;


    private String profileImageUrl;

    // Owning side: following
    @ManyToMany(fetch = FetchType.LAZY , cascade = { CascadeType.PERSIST, CascadeType.MERGE })
    @JoinTable(
            name = "user_followers",
            joinColumns = @JoinColumn(name = "follower_id"),       // who follows
            inverseJoinColumns = @JoinColumn(name = "following_id") // who is being followed
    )
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Set<User> following = new HashSet<>();

    // Inverse side: followers
    @ManyToMany(mappedBy = "following", fetch = FetchType.LAZY)
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @JsonIgnore
    private Set<User> followers = new HashSet<>();


    // Add convenience methods for managing followers and following if needed
}