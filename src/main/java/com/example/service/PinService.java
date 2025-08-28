package com.example.service;

import com.example.model.Pin;
import java.util.List;

public interface PinService {
    Pin createPin(Pin pin, String userId);
    List<Pin> getAllPins();
    Pin getPinById(String id);
    Pin updatePin(String id, Pin updatedPin);
    void deletePin(String id);
    List<Pin> getPinsByUser(String userId);
    List<Pin> searchPins(String keyword);
    Pin likePin(String pinId, String userId);
    Pin unlikePin(String pinId, String userId);
    Pin savePinToBoard(String pinId, String boardId);
    Pin removePinFromBoard(String pinId, String boardId);
}