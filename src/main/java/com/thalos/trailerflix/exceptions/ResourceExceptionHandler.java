package com.thalos.trailerflix.exceptions;

import com.thalos.trailerflix.exceptions.exception.InternalServerException;
import com.thalos.trailerflix.exceptions.exception.ObjectNotFoundException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ResourceExceptionHandler {
    @ExceptionHandler(ObjectNotFoundException.class)
    public ResponseEntity<StandardError> objectNotFoundException(ObjectNotFoundException e) {
        StandardError standardError = new StandardError(System.currentTimeMillis(),
                HttpStatus.NOT_FOUND.value(), e.getMessage());

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(standardError);
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<StandardError> dataIntegrityViolationException(DataIntegrityViolationException e) {
        StandardError standardError = new StandardError(System.currentTimeMillis(),
                HttpStatus.BAD_REQUEST.value(), e.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(standardError);
    }

    @ExceptionHandler(InternalServerException.class)
    public ResponseEntity<StandardError> internalServerException(InternalServerException e) {
        StandardError standardError = new StandardError(System.currentTimeMillis(),
                HttpStatus.INTERNAL_SERVER_ERROR.value(), e.getMessage());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(standardError);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<StandardError> methodArgumentNotValidException(MethodArgumentNotValidException e) {
        ValidationError validationError = new ValidationError(System.currentTimeMillis(),
                HttpStatus.BAD_REQUEST.value(), "Erro na validação dos campos");

        for (FieldError fieldMessage : e.getBindingResult().getFieldErrors()) {
            validationError.addError(fieldMessage.getField(), fieldMessage.getDefaultMessage());
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(validationError);
    }
}
