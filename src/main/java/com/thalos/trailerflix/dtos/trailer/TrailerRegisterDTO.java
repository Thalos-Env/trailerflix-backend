package com.thalos.trailerflix.dtos.trailer;

import java.time.LocalDate;
import java.util.UUID;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class TrailerRegisterDTO {
	
	public TrailerRegisterDTO(UUID user, String url, LocalDate releaseDate) {
		this.user = user;
		this.url = url;
		this.releaseDate = releaseDate;
	}
	
	private UUID user;
	private String url;
	private LocalDate releaseDate;
}