package com.example.controller;

import com.example.model.Pin;
import com.example.service.PinService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/pins")
public class PinController {

    @Autowired
    private PinService pinService;

    // Create Pin (temporarily pass userId as a request param)
    @PostMapping
    public Pin createPin(@RequestBody Pin pin, @RequestParam String userId) {
        return pinService.createPin(pin, userId);
    }

    // Get all Pins
    @GetMapping
    public List<Pin> getAllPins() {
        return pinService.getAllPins();
    }

    // Get Pin by ID
    @GetMapping("/{id}")
    public Pin getPinById(@PathVariable String id) {
        return pinService.getPinById(id);
    }

    // Update Pin
    @PutMapping("/{id}")
    public Pin updatePin(@PathVariable String id, @RequestBody Pin pin) {
        return pinService.updatePin(id, pin);
    }

    // Delete Pin
    @DeleteMapping("/{id}")
    public void deletePin(@PathVariable String id) {
        pinService.deletePin(id);
    }

    // Get Pins by User
    @GetMapping("/user/{userId}")
    public List<Pin> getPinsByUser(@PathVariable String userId) {
        return pinService.getPinsByUser(userId);
    }

    // Search Pins
    @GetMapping("/search")
    public List<Pin> searchPins(@RequestParam String query) {
        return pinService.searchPins(query);
    }

    // Like a Pin (pass userId for now)
    @PostMapping("/{id}/like")
    public void likePin(@PathVariable String id, @RequestParam String userId) {
        pinService.likePin(id, userId);
    }

    // Unlike a Pin (pass userId for now)
    @DeleteMapping("/{id}/unlike")
    public void unlikePin(@PathVariable String id, @RequestParam String userId) {
        pinService.unlikePin(id, userId);
    }

    // Save Pin to Board (pass userId for now)
    @PostMapping("/{id}/save/{boardId}")
    public void savePinToBoard(@PathVariable String id, @PathVariable String boardId, @RequestParam String userId) {
 pinService.savePinToBoard(id, boardId); // Note: userId is passed to the service if needed for authorization checks
    }

    // Remove Pin from Board (pass userId for now)
    @DeleteMapping("/{id}/unsave/{boardId}")
    public void removePinFromBoard(@PathVariable String id, @PathVariable String boardId, @RequestParam String userId) {
 pinService.removePinFromBoard(id, boardId); // Note: userId is passed to the service if needed for authorization checks
    }
}
