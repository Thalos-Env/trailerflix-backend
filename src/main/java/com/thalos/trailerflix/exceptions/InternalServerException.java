package com.thalos.trailerflix.exceptions;

public class InternalServerException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public InternalServerException(String message, Throwable cause) {
        super(message, cause);
    }

    public InternalServerException(String message) {
        super(message);
    }
}
