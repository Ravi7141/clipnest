package com.example.controller;

import com.example.model.Pin;
import com.example.service.PinService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/pins")
public class PinController {

    @Autowired
    private PinService pinService;

    // Create Pin (temporarily pass userId as a request param)
    @PostMapping("/create/{userId}")
    public ResponseEntity<?> createPin(@RequestBody Pin pin, @PathVariable String userId) {
        Pin created = pinService.createPin(pin, userId);
        if(created!= null){
            return new ResponseEntity<>(HttpStatus.CREATED);
        }
        else{
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    // Get all Pins
    @GetMapping
    public ResponseEntity<?> getAllPins() {
        List<Pin> listFound = pinService.getAllPins();
        if(listFound != null) {
            return new ResponseEntity<>(listFound, HttpStatus.FOUND);
        }
        else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Get Pin by ID
    @GetMapping("/{id}")
    public ResponseEntity<?> getPinById(@PathVariable String id) {
        Pin found = pinService.getPinById(id);
        if(found != null){
            return new ResponseEntity<>(found, HttpStatus.FOUND);
        }
        else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Update Pin
    @PutMapping("/{id}")
    public ResponseEntity<?> updatePin(@PathVariable String id, @RequestBody Pin pin) {
        Pin updatedPin = pinService.updatePin(id, pin);
        if(updatedPin != null){
            return new ResponseEntity<>(updatedPin, HttpStatus.OK);
        }
        else{
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    // Delete Pin
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletePin(@PathVariable String id) {
        try{
            pinService.deletePin(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        catch (Exception e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Get Pins by User
    @GetMapping("/user/{userId}")
    public ResponseEntity<?> getPinsByUser(@PathVariable String userId) {
        try{
            List<Pin> list = pinService.getPinsByUser(userId);
            return new ResponseEntity<>(list,HttpStatus.FOUND);
        }
        catch (Exception e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Search Pins
    @GetMapping("/search")
    public ResponseEntity<?> searchPins(@RequestParam String query) {
        try{
            List<Pin> list = pinService.searchPins(query);
            return new ResponseEntity<>(list, HttpStatus.FOUND);
        }
        catch (Exception e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Like a Pin (pass userId for now)
    @PostMapping("/{id}/like/{userId}")
    public ResponseEntity<?> likePin(@PathVariable String id, @PathVariable String userId) {
        Pin likedPin = pinService.likePin(id, userId);
        if(likedPin != null){
            return new ResponseEntity<>(HttpStatus.OK);
        }
        else{
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    // Unlike a Pin (pass userId for now)
    @DeleteMapping("/{id}/unlike/{userId}")
    public ResponseEntity<?> unlikePin(@PathVariable String id, @PathVariable String userId) {
        Pin unlikedPin = pinService.unlikePin(id, userId);
        if(unlikedPin != null){
            return new ResponseEntity<>(HttpStatus.OK);
        }
        else{
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    // Save Pin to Board (pass userId for now)
    @PostMapping("/{id}/save/{boardId}")
    public ResponseEntity<?> savePinToBoard(@PathVariable String id, @PathVariable String boardId, @RequestParam Long userId) {
        Pin savedPinToBoard = pinService.savePinToBoard(id, boardId); // Note: userId is passed to the service if needed for authorization checks
        if(savedPinToBoard != null){
            return new ResponseEntity<>(HttpStatus.OK);
        }
        else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    // Remove Pin from Board (pass userId for now)
    @DeleteMapping("/{id}/unsave/{boardId}")
    public ResponseEntity<?> removePinFromBoard(@PathVariable String id, @PathVariable String boardId, @RequestParam String userId) {

        Pin removedPinFromBoard = pinService.removePinFromBoard(id, boardId); // Note: userId is passed to the service if needed for authorization checks
        if(removedPinFromBoard != null){
            return new ResponseEntity<>(HttpStatus.OK);
        }
        else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}