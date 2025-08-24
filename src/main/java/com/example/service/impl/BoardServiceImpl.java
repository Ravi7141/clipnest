package com.example.service.impl;

import com.example.model.Board;
import com.example.model.User;
import com.example.repository.BoardRepository;
import com.example.repository.UserRepository;
import com.example.service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BoardServiceImpl implements BoardService {

    private final BoardRepository boardRepository;
    private final UserRepository userRepository;

    @Autowired
    public BoardServiceImpl(BoardRepository boardRepository, UserRepository userRepository) {
        this.boardRepository = boardRepository;
        this.userRepository = userRepository;
    }

    @Override
    public Board createBoard(Board board, Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));
        board.setCreatedBy(user);
        return boardRepository.save(board);
    }

    @Override
    public List<Board> getBoardsByUser(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));
        return boardRepository.findByCreatedBy(user);
    }

    @Override
    public Board getBoardById(Long id) {
        return boardRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Board not found"));
    }

    @Override
    public Board updateBoard(Long id, Board updatedBoard) {
        Board existingBoard = getBoardById(id);
        existingBoard.setName(updatedBoard.getName());
        existingBoard.setDescription(updatedBoard.getDescription());
        // Note: Ownership (createdBy) is not changed via update
        return boardRepository.save(existingBoard);
    }

    @Override
    public void deleteBoard(Long id) {
        Board board = getBoardById(id);
        boardRepository.delete(board);
    }

    @Override
    public void addPinToBoard(Long boardId, Long pinId) {
        // TODO: Implement adding a pin to a board
        throw new UnsupportedOperationException("Adding pin to board not yet implemented");
    }

    @Override
    public void removePinFromBoard(Long boardId, Long pinId) {
        // TODO: Implement removing a pin from a board
        throw new UnsupportedOperationException("Removing pin from board not yet implemented");
    }
}