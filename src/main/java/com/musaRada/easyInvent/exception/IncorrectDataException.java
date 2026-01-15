package com.musaRada.easyInvent.exception;

import lombok.AllArgsConstructor;
import lombok.Data;


@AllArgsConstructor
public class IncorrectDataException extends RuntimeException {
    public IncorrectDataException(String message) {
        super(message);
    }
}
