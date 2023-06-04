package com.thalos.trailerflix.dtos.external;

import java.util.List;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Data
public class ResultsExternalApiDTO {

	private List<Object> results;
}
