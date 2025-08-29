package com.example.service.impl;

import com.example.model.Board;
import com.example.model.Pin;
import com.example.repository.BoardRepository;
import com.example.repository.PinRepository;
import com.example.repository.UserRepository;
import com.example.service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class BoardServiceImpl implements BoardService {

    private final BoardRepository boardRepository;
    private final UserRepository userRepository;

    @Autowired
    private PinRepository pinRepository;

    @Autowired
    public BoardServiceImpl(BoardRepository boardRepository, UserRepository userRepository) {
        this.boardRepository = boardRepository;
        this.userRepository = userRepository;
    }

    @Override
    @Transactional
    public Board createBoard(Board board, String userId) {
        // In MongoDB, we can embed or reference the user ID.
        // For simplicity, let's assume we store the user ID in the Board document.
        board.setCreatedBy(userId); // Store user ID as String in Board
        return boardRepository.save(board);
    }

    @Override
    public List<Board> getBoardsByUser(String userId) {
        return boardRepository.findByCreatedBy(userId);
    }

    @Override
    public Board getBoardById(String id) {
        return boardRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Board not found"));
    }

    @Override
    @Transactional
    public Board updateBoard(String id, Board updatedBoard) {
        Board existingBoard = getBoardById(id);
        existingBoard.setName((updatedBoard.getName()!=null) ? updatedBoard.getName() : existingBoard.getName());
        existingBoard.setDescription((updatedBoard.getDescription()!=null) ? updatedBoard.getDescription() : existingBoard.getDescription());
        return boardRepository.save(existingBoard);
    }

    @Override
    public void deleteBoard(String id) {
        Board board = getBoardById(id);
        boardRepository.delete(board);
    }

}