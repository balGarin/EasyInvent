package com.musaRada.easyInvent.controller;

import com.musaRada.easyInvent.dto.in.UserDtoSingUp;
import com.musaRada.easyInvent.service.interfaces.AuthService;
import jakarta.transaction.NotSupportedException;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@AllArgsConstructor
public class AuthController {
    private AuthService authService;

    @PostMapping(path = "/singIn")
    public ResponseEntity<Object> singIn() {
        return ResponseEntity.status(200).body("Ok");
    }

    @PostMapping(path = "/singUp")
    public ResponseEntity<Object> singUp(@Valid @RequestBody UserDtoSingUp userDtoSingUp) {
        return ResponseEntity.status(200).body(authService.singUp(userDtoSingUp));
    }
}
