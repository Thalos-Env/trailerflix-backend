package com.thalos.trailerflix.exceptions.exception;

public class DataIntegrityViolationException extends RuntimeException {
//    private static final long serialVersionUid = 1L;

    public DataIntegrityViolationException(String message, Throwable cause) {
        super(message, cause);
    }

    public DataIntegrityViolationException(String message) {
        super(message);
    }
}
