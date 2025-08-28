package com.example.service.impl;

import com.example.model.Board;
import com.example.model.Pin;
import com.example.model.User;
import com.example.repository.BoardRepository;
import com.example.repository.PinRepository;
import com.example.repository.UserRepository;
import com.example.service.PinService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PinServiceImpl implements PinService {

    private final PinRepository pinRepository;
    private final UserRepository userRepository;

    @Autowired
    private BoardRepository boardRepository;

    @Autowired
    public PinServiceImpl(PinRepository pinRepository, UserRepository userRepository) {
        this.pinRepository = pinRepository;
        this.userRepository = userRepository;
    }

    @Override
    public Pin createPin(Pin pin, String userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));
        pin.setCreatedBy(user);
        return pinRepository.save(pin);
    }

    @Override
    public List<Pin> getAllPins() {
        return pinRepository.findAll();
    }

    @Override
    public Pin getPinById(String id) {
        return pinRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Pin not found"));
    }

    @Override
    public Pin updatePin(String id, Pin updatedPin) {
        Pin existingPin = pinRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Pin not found"));
        existingPin.setTitle(updatedPin.getTitle());
        existingPin.setDescription(updatedPin.getDescription());
        existingPin.setImageUrl(updatedPin.getImageUrl());
        return pinRepository.save(existingPin);
    }

    @Override
    public void deletePin(String id) {
        pinRepository.deleteById(id);
    }

    @Override
    public List<Pin> getPinsByUser(String userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));
        return pinRepository.findByCreatedBy(user);
    }

    @Override
    public List<Pin> searchPins(String keyword) {
        return pinRepository.findByTitleContainingIgnoreCaseOrDescriptionContainingIgnoreCase(keyword, keyword);
    }

    @Override
    public Pin likePin(String pinId, String userId) {
        Pin currPin = pinRepository.findById(pinId)
                .orElseThrow(() -> new RuntimeException("Pin not found"));
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException("User not found"));
        currPin.getLikedByUsers().add(userId);
        return pinRepository.save(currPin);

    }

    @Override
    public Pin unlikePin(String pinId, String userId) {
        Pin currPin = pinRepository.findById(pinId)
                .orElseThrow(() -> new RuntimeException("Pin not found"));
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException("User not found"));
        currPin.getLikedByUsers().remove(userId);
        return pinRepository.save(currPin);
    }

    @Override
    public Pin savePinToBoard(String pinId, String boardId) {
        Pin pin = getPinById(pinId);
        Board board = boardRepository.findById(boardId)
                .orElseThrow(() -> new EntityNotFoundException("Board not found with id: " + boardId));

        board.getPins().add(pin);
        pin.getBoardIds().add(boardId);

        boardRepository.save(board);
        return pinRepository.save(pin);
    }

    @Override
    public Pin removePinFromBoard(String pinId, String boardId) {
        Pin pin = getPinById(pinId);
        Board board = boardRepository.findById(boardId)
                .orElseThrow(() -> new EntityNotFoundException("Board not found with id: " + boardId));

        board.getPins().remove(pin);
        pin.getBoardIds().remove(boardId);

        boardRepository.save(board);
        return pinRepository.save(pin);
    }
}