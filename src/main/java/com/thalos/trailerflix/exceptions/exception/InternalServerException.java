package com.thalos.trailerflix.exceptions.exception;

public class InternalServerException extends RuntimeException {
    public InternalServerException(String message, Throwable cause) {
        super(message, cause);
    }

    public InternalServerException(String message) {
        super(message);
    }
}
