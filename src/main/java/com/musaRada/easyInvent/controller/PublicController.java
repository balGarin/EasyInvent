package com.musaRada.easyInvent.controller;

import com.musaRada.easyInvent.dto.in.BottleDtoIn;
import com.musaRada.easyInvent.dto.in.UserUpdateDto;
import com.musaRada.easyInvent.repository.UserEmailRepository;
import com.musaRada.easyInvent.service.interfaces.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/bottle")
@AllArgsConstructor
public class PublicController {
    private  final UserService userService;


    public ResponseEntity<Object> findBottleByBarcode(String barcode) {
        return null;
    }

    public ResponseEntity<Object> findBottleByTitle(String title) {
        return null;
    }

    public ResponseEntity<Object> addBottle(BottleDtoIn bottleDtoIn) {
        return null;
    }

    public ResponseEntity<Object> calculateBottle(Long bottleId, String currentWeight) {

        return null;
    }

    public ResponseEntity<Object> updateUser(UserUpdateDto userUpdateDto){
        return  null;
    }
}
