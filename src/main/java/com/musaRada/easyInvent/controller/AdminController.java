package com.musaRada.easyInvent.controller;

import com.musaRada.easyInvent.dto.in.BottleDtoIn;
import com.musaRada.easyInvent.dto.in.BottleUpdateDto;
import com.musaRada.easyInvent.model.Bottle;
import com.musaRada.easyInvent.model.UserEmail;
import com.musaRada.easyInvent.service.interfaces.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/admin")
@AllArgsConstructor
public class AdminController {

    private final UserService userService;


    public ResponseEntity<Object> approveBottle(List<Long> bottleIds) {
        return null;
    }

    public ResponseEntity<Object> getAllWaitingBottles() {
        return null;
    }

    @PostMapping("/email")
    public ResponseEntity<Object> addEmailForSingUp(@RequestBody UserEmail email) {
        return ResponseEntity.status(201).body(userService.addEmailForSingUp(email));
    }

    public ResponseEntity<Object> getAllUsers() {
       return ResponseEntity.status(200).body(userService.getAllUsers());
    }

    public ResponseEntity<Object> removeUser(Long userId) {
        return ResponseEntity.status(200).body(userService.removeUser(userId));
    }

    public ResponseEntity<Object> addBottle(BottleDtoIn bottleDtoIn) {
        return null;
    }

    public ResponseEntity<Object> getAllBottles() {
        return null;
    }

    public ResponseEntity<Object> removeBottle(Long bottleId) {
        return null;
    }

    public ResponseEntity<Object> updateBottle(BottleUpdateDto bottleUpdateDto) {
        return null;
    }

    public ResponseEntity<Object> getUserById(Long userId) {
       return ResponseEntity.status(200).body(userService.getUserById(userId));
    }
}
