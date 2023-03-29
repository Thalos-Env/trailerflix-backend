package com.thalos.trailerflix.dtos.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class FieldMessageDTO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private String fieldName;
    private String message;
}
