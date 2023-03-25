package com.thalos.trailerflix.exceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ValidationError extends StandardError{
    private static final long serialVersionUid = 1L;
    private List<FieldMessage> errors = new ArrayList<>();
    public ValidationError(Long timestamp, Integer status, String error) {
        super(timestamp, status, error);
    }
    public void addError(String fieldName, String message) {
        this.errors.add(new FieldMessage(fieldName, message));
    }
}
