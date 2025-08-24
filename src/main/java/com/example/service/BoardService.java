package com.example.service;

import com.example.model.Board;
import java.util.List;

public interface BoardService {
    Board createBoard(Board board, Long userId);
    List<Board> getBoardsByUser(Long userId);
    Board getBoardById(Long id);
    Board updateBoard(Long id, Board updatedBoard);
    void deleteBoard(Long id);
    void addPinToBoard(Long boardId, Long pinId);
    void removePinFromBoard(Long boardId, Long pinId);
}