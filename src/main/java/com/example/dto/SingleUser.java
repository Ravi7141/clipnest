package com.example.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SingleUser {
    private Long id;
    private String username;
    private Set<Long> followingIds;
    private Set<Long> followerIds;
}
