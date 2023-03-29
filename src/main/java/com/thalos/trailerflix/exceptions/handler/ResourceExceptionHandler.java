package com.thalos.trailerflix.exceptions.handler;

import com.thalos.trailerflix.dtos.exception.StandardErrorDTO;
import com.thalos.trailerflix.dtos.exception.ValidationErrorDTO;
import com.thalos.trailerflix.exceptions.InternalServerException;
import com.thalos.trailerflix.exceptions.ObjectNotFoundException;

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
    public ResponseEntity<StandardErrorDTO> objectNotFoundException(ObjectNotFoundException e) {
        StandardErrorDTO standardError = new StandardErrorDTO(System.currentTimeMillis(),
                HttpStatus.NOT_FOUND.value(), e.getMessage());

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(standardError);
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<StandardErrorDTO> dataIntegrityViolationException(DataIntegrityViolationException e) {
        StandardErrorDTO standardError = new StandardErrorDTO(System.currentTimeMillis(),
                HttpStatus.BAD_REQUEST.value(), e.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(standardError);
    }

    @ExceptionHandler(InternalServerException.class)
    public ResponseEntity<StandardErrorDTO> internalServerException(InternalServerException e) {
        StandardErrorDTO standardError = new StandardErrorDTO(System.currentTimeMillis(),
                HttpStatus.INTERNAL_SERVER_ERROR.value(), e.getMessage());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(standardError);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<StandardErrorDTO> methodArgumentNotValidException(MethodArgumentNotValidException e) {
        ValidationErrorDTO validationError = new ValidationErrorDTO(System.currentTimeMillis(),
                HttpStatus.BAD_REQUEST.value(), "Erro na validação dos campos");

        for (FieldError fieldMessage : e.getBindingResult().getFieldErrors()) {
            validationError.addError(fieldMessage.getField(), fieldMessage.getDefaultMessage());
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(validationError);
    }
}
