package com.example.controller;

import com.example.model.Board;
import com.example.model.Pin;
import com.example.repository.BoardRepository;
import org.apache.tomcat.util.http.parser.HttpParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.service.BoardService;

import java.util.List;

@RestController
@RequestMapping("/api/boards")
public class BoardController {

    @Autowired
    private BoardService boardService;

    @Autowired
    private BoardRepository boardRepository;

    @PostMapping("/create/{userId}")
    public ResponseEntity<?> createBoard(@RequestBody Board board, @PathVariable String userId) {
        Board savedBoard = boardService.createBoard(board, userId);
        if (savedBoard != null) {
            return new ResponseEntity<>(savedBoard, HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<?> getBoardsByUser(@PathVariable String userId) {
        List<Board> list = boardService.getBoardsByUser(userId);
        if(list != null)
            return new ResponseEntity<>(list, HttpStatus.FOUND);
        else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getBoardById(@PathVariable String id) {
        Board board = boardService.getBoardById(id);
        if(board!=null)
            return new ResponseEntity<>(board, HttpStatus.FOUND);
        else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateBoard(@PathVariable String id, @RequestBody Board board) {
        Board updateBoard = boardService.updateBoard(id, board);
        if(updateBoard!=null)
            return new ResponseEntity<>(updateBoard, HttpStatus.OK);
        else
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteBoard(@PathVariable String id) {
        try{
            boardService.deleteBoard(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        catch (Exception e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}