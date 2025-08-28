package com.example.service.impl;

import com.example.model.Board;
import com.example.repository.BoardRepository;
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
    public BoardServiceImpl(BoardRepository boardRepository, UserRepository userRepository) {
        this.boardRepository = boardRepository;
        this.userRepository = userRepository;
    }

    @Override
    @Transactional
    public Board createBoard(Board board, Long userId) {
        // In MongoDB, we can embed or reference the user ID.
        // For simplicity, let's assume we store the user ID in the Board document.
        board.setCreatedBy(userId.toString()); // Store user ID as String in Board
        return boardRepository.save(board);
    }

    @Override
    public List<Board> getBoardsByUser(Long userId) {
        return boardRepository.findByCreatedBy(userId.toString());
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
        existingBoard.setName(updatedBoard.getName());
        existingBoard.setDescription(updatedBoard.getDescription());
        return boardRepository.save(existingBoard);
    }

    @Override
    public void deleteBoard(Long id) {
//        Board board = getBoardById(id);
//        boardRepository.delete(board);
    }

    @Override
    public void addPinToBoard(String boardId, String pinId) {
        // TODO: Implement adding a pin to a board
        throw new UnsupportedOperationException("Adding pin to board not yet implemented");
    }

    @Override
    public void removePinFromBoard(String boardId, String pinId) {
        // TODO: Implement removing a pin from a board
        throw new UnsupportedOperationException("Removing pin from board not yet implemented");
    }
}