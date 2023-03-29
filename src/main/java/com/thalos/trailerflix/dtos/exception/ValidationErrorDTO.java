package com.thalos.trailerflix.dtos.exception;

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
public class ValidationErrorDTO extends StandardErrorDTO {
	private static final long serialVersionUID = 1L;

	private List<FieldMessageDTO> errors = new ArrayList<>();

	public ValidationErrorDTO(Long timestamp, Integer status, String error) {
		super(timestamp, status, error);
	}

	public void addError(String fieldName, String message) {
		this.errors.add(new FieldMessageDTO(fieldName, message));
	}
}
