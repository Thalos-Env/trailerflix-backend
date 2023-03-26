package com.thalos.trailerflix.exceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class StandardError implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    private Long timestamp;
    private Integer status;
    private String error;
}
