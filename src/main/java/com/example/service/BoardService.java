package com.example.service;

import com.example.model.Board;
import java.util.List;

public interface BoardService {
    Board createBoard(Board board, String userId);
    List<Board> getBoardsByUser(String userId);
    Board getBoardById(String id);
    Board updateBoard(String id, Board updatedBoard);
    void deleteBoard(String id);
    void addPinToBoard(String boardId, String pinId);
    void removePinFromBoard(String boardId, String pinId);
}