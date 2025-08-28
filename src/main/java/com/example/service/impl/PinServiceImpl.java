package com.example.service.impl;

import com.example.model.Pin;
import com.example.model.Board;
import com.example.model.User;
import com.example.repository.PinRepository;
import com.example.repository.UserRepository;
import com.example.service.PinService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PinServiceImpl implements PinService {

    private final PinRepository pinRepository;
    private final UserRepository userRepository;

    @Autowired
    public PinServiceImpl(PinRepository pinRepository, UserRepository userRepository) {
        this.pinRepository = pinRepository;
        this.userRepository = userRepository;
    }

    @Override
    public Pin createPin(Pin pin, Long userId) {
        // In MongoDB, we might store the user ID directly in the Pin document
        pin.setCreatedBy(userId.toString()); // Assuming user ID is stored as String in Pin
        return pinRepository.save(pin);
    }

    @Override
    public List<Pin> getAllPins() {
 return pinRepository.findAll();
    }

    @Override
    public Pin getPinById(Long id) {
        return pinRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Pin not found"));
    }

    @Override
    public Pin updatePin(Long id, Pin updatedPin) {
        Pin existingPin = pinRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Pin not found"));
        existingPin.setTitle(updatedPin.getTitle());
        existingPin.setDescription(updatedPin.getDescription());
        existingPin.setImageUrl(updatedPin.getImageUrl());
        return pinRepository.save(existingPin);
    }

    @Override
    public void deletePin(Long id) {
        pinRepository.deleteById(id);
    }

    @Override
    public List<Pin> getPinsByUser(Long userId) {
 return pinRepository.findByCreatedBy(userId.toString()); // Assuming user ID is stored as String
    }

    @Override
    public List<Pin> searchPins(String keyword) {
 return pinRepository.findByTitleContainingIgnoreCase(keyword);
    }

    @Override
    public void likePin(Long pinId, Long userId) {
        throw new UnsupportedOperationException("likePin not implemented yet");
    }

    @Override
    public void unlikePin(Long pinId, Long userId) {
        throw new UnsupportedOperationException("unlikePin not implemented yet");
    }

    @Override
    public void savePinToBoard(Long pinId, Long boardId) {
        throw new UnsupportedOperationException("savePinToBoard not implemented yet");
    }

    @Override
    public void removePinFromBoard(Long pinId, Long boardId) {
        throw new UnsupportedOperationException("removePinFromBoard not implemented yet");
    }
}