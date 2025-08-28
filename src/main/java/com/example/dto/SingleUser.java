package com.example.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SingleUser {
    private String id;
    private String username;
    private Set<String> followingIds;
    private Set<String> followerIds;
}
