package com.thalos.trailerflix.exceptions.exception;

public class ObjectNotFoundException extends RuntimeException {
//    private static final long serialVersionUid = 1L;

    public ObjectNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public ObjectNotFoundException(String message) {
        super(message);
    }
}
