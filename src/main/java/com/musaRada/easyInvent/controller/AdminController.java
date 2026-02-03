package com.musaRada.easyInvent.controller;

import com.musaRada.easyInvent.dto.in.BottleDtoIn;
import com.musaRada.easyInvent.dto.in.BottleUpdateDto;
import com.musaRada.easyInvent.dto.in.UserEmailDtoIn;
import com.musaRada.easyInvent.model.Bottle;
import com.musaRada.easyInvent.model.UserEmail;
import com.musaRada.easyInvent.service.interfaces.BottleService;
import com.musaRada.easyInvent.service.interfaces.UserService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/admin")
@AllArgsConstructor
public class AdminController {

    private final UserService userService;
    private final BottleService bottleService;

    @PutMapping("/approveBottle")
    public ResponseEntity<Object> approveBottle(ArrayList<Long> bottleIds) {
        return ResponseEntity.status(200).body(bottleService.approveBottle(bottleIds));
    }

    @GetMapping("/waitingBottles")
    public ResponseEntity<Object> getAllWaitingBottles() {
        return ResponseEntity.status(200).body(bottleService.getAllWaitingBottles());
    }

    @PostMapping("/approveEmail")
    public ResponseEntity<Object> addEmailForSingUp(@RequestBody UserEmailDtoIn email) {
        return ResponseEntity.status(201).body(userService.addEmailForSingUp(email));
    }

    @GetMapping("/users")
    public ResponseEntity<Object> getAllUsers() {
        return ResponseEntity.status(200).body(userService.getAllUsers());
    }

    @DeleteMapping("/users/{userId}")
    public ResponseEntity<Object> removeUser(@PathVariable Long userId) {
        return ResponseEntity.status(200).body(userService.removeUser(userId));
    }

    @PostMapping("/bottles")
    public ResponseEntity<Object> addBottle(BottleDtoIn bottleDtoIn) {
        return ResponseEntity.status(201).body(bottleService.addBottle(bottleDtoIn));
    }

    public ResponseEntity<Object> getAllBottles(@RequestParam(defaultValue = "0") Integer from, @RequestParam(defaultValue = "10") Integer limit) {
        return ResponseEntity.status(200).body(bottleService.getAllBottles(from, limit));
    }

    public ResponseEntity<Object> removeBottle(Long bottleId) {
        return ResponseEntity.status(200).body(bottleService.removeBottle(bottleId));
    }

    @PutMapping("/bottle/{bottleId}")
    public ResponseEntity<Object> updateBottle(@PathVariable Long bottleId,@RequestBody @Valid BottleDtoIn bottleDtoIn) {
      return ResponseEntity.status(200).body(bottleService.updateBottle(bottleId,bottleDtoIn));
    }

    @GetMapping("/users/{userId}")
    public ResponseEntity<Object> getUserById(@PathVariable Long userId) {
        return ResponseEntity.status(200).body(userService.getUserById(userId));
    }
}
