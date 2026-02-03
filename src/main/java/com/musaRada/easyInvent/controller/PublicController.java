package com.musaRada.easyInvent.controller;

import com.musaRada.easyInvent.dto.in.BottleDtoIn;
import com.musaRada.easyInvent.dto.in.RequestCalculateBottle;
import com.musaRada.easyInvent.dto.in.UserUpdateDto;
import com.musaRada.easyInvent.repository.UserEmailRepository;
import com.musaRada.easyInvent.service.interfaces.BottleService;
import com.musaRada.easyInvent.service.interfaces.UserService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/public")
@AllArgsConstructor
@Slf4j
public class PublicController {
    private final UserService userService;
    private final BottleService bottleService;

    @GetMapping("/bottles/barcode")
    public ResponseEntity<Object> findBottleByBarcode(@RequestParam String value) {
        return ResponseEntity.status(200).body(bottleService.findBottleByBarcode(value));
    }

    @GetMapping("/bottles/title")
    public ResponseEntity<Object> findBottleByTitle(@RequestParam String value) {
        return ResponseEntity.status(200).body(bottleService.findBottleByTitle(value));
    }

    @PostMapping("/bottles")
    public ResponseEntity<Object> addBottle(@Valid @RequestBody BottleDtoIn bottleDtoIn) {
        log.info("Бутылка -> {}", bottleDtoIn);
        return ResponseEntity.status(201).body(bottleService.addBottle(bottleDtoIn));
    }

    @GetMapping("/bottles/calculate")
    public ResponseEntity<Object> calculateBottle(@RequestBody @Valid RequestCalculateBottle calculateBottle) {

       return ResponseEntity.status(200).body(bottleService.calculateBottle(calculateBottle)) ;
    }


    @PutMapping("/bottles/{bottleId}")
    public ResponseEntity<Object>updateBottle(@PathVariable Long bottleId,@Valid @RequestBody BottleDtoIn bottleDtoIn){
        return ResponseEntity.status(200).body(bottleService.updateBottle(bottleId,bottleDtoIn));
    }

//    public ResponseEntity<Object> updateUser(UserUpdateDto userUpdateDto) {
//        userService.updateUser(userUpdateDto);
//    }
}
