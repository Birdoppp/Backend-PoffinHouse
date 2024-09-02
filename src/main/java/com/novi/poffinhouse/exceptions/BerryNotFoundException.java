package com.novi.poffinhouse.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;

public class BerryNotFoundException extends RuntimeException {
    public BerryNotFoundException(Long id) {
        super("Berry with id " + id + " not found.");
    }

    @ExceptionHandler(BerryNotFoundException.class)
    public ResponseEntity<String> handleBerryNotFoundException(BerryNotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }
}