package com.novi.poffinhouse.exceptions;

public class AccessDeniedException extends RuntimeException {

    public AccessDeniedException() {
        super("You do not have permission to access this resource.");
    }

}
