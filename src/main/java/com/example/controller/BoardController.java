package com.example.controller;

import org.springframework.web.bind.annotation.*;
import com.example.service.BoardService;

@RestController
@RequestMapping("/api/boards")
public class BoardController {

    @PostMapping
    public void createBoard() {
        // Method stub for createBoard
    }

    @GetMapping("/user/{userId}")
    public void getBoardsByUser(@PathVariable Long userId) {
        // Method stub for getBoardsByUser
    }

    @GetMapping("/{id}")
    public void getBoardById(@PathVariable Long id) {
        // Method stub for getBoardById
    }

    @PutMapping("/{id}")
    public void updateBoard(@PathVariable Long id) {
        // Method stub for updateBoard
    }

    @DeleteMapping("/{id}")
    public void deleteBoard(@PathVariable Long id) {
        // Method stub for deleteBoard
    }

    @PostMapping("/{id}/pins/{pinId}")
    public void addPinToBoard(@PathVariable Long id, @PathVariable Long pinId) {
        // Method stub for addPinToBoard
    }

    @DeleteMapping("/{id}/pins/{pinId}")
    public void removePinFromBoard(@PathVariable Long id, @PathVariable Long pinId) {
        // Method stub for removePinFromBoard
    }
}