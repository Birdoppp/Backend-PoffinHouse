package com.novi.poffinhouse.exceptions;

public class BerryNotFoundException extends RuntimeException {
    public BerryNotFoundException(Long id) {
        super("Berry with id " + id + " not found.");
    }
}
