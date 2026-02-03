package com.musaRada.easyInvent.exception;
import jakarta.validation.ValidationException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Slf4j
@RestControllerAdvice(basePackages = "com.musaRada.easyInvent")
public class ErrorHandler {

    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");



    @org.springframework.web.bind.annotation.ExceptionHandler(value = {ValidationException.class,
            MethodArgumentNotValidException.class,
            NumberFormatException.class,
            DataIntegrityViolationException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse handleValidate(Throwable exception) {
        log.warn("Произошло исключение : {}", exception.getMessage());
        return new ErrorResponse(HttpStatus.BAD_REQUEST, "Incorrectly made request.",
                exception.getMessage(), LocalDateTime.now().format(formatter));
    }


    @org.springframework.web.bind.annotation.ExceptionHandler
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorResponse handleNotFound(NotFoundException exception) {
        log.warn("Произошло исключение : {}", exception.getMessage());
        return new ErrorResponse(HttpStatus.NOT_FOUND, "Object had not found",
                exception.getMessage(), LocalDateTime.now().format(formatter));
    }

    @org.springframework.web.bind.annotation.ExceptionHandler
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse handleIncorrectDateException(IncorrectDataException exception) {
        log.warn("Произошло исключение : {}", exception.getMessage());
        return new ErrorResponse(HttpStatus.BAD_REQUEST, "Incorrectly made request.",
                exception.getMessage(), LocalDateTime.now().format(formatter));
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.FORBIDDEN)
    public ErrorResponse handleAuthException(AuthException exception){
        log.warn("Произошло исключение : {}", exception.getMessage());
        return  new ErrorResponse(HttpStatus.FORBIDDEN,"You do not have access",
                exception.getMessage(),LocalDateTime.now().format(formatter));
    }
}
