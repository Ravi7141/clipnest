package com.example.service;

import com.example.model.Pin;
import java.util.List;

public interface PinService {
    Pin createPin(Pin pin, Long userId);
    List<Pin> getAllPins();
    Pin getPinById(Long id);
    Pin updatePin(Long id, Pin updatedPin);
    void deletePin(Long id);
    List<Pin> getPinsByUser(Long userId);
    List<Pin> searchPins(String keyword);
    void likePin(Long pinId, Long userId);
    void unlikePin(Long pinId, Long userId);
    void savePinToBoard(Long pinId, Long boardId);
    void removePinFromBoard(Long pinId, Long boardId);
}