package com.thalos.trailerflix.dtos.trailer;

import java.time.LocalDate;
import java.util.UUID;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class TrailerRegisterDTO {
	
	private UUID id;
	private String url;
	private LocalDate releaseDate;
	private LocalDate uploadDate;
}
