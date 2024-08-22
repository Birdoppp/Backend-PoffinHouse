package com.novi.poffinhouse.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT) // 409
public class SlotOccupiedException extends RuntimeException {
    public SlotOccupiedException(String message) {
        super(message);
    }
}
