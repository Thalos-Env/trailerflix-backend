package com.thalos.trailerflix.dtos.trailer;

import java.time.LocalDate;
import java.util.UUID;

import com.thalos.trailerflix.entities.User;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class TrailerConsultDTO {

	public TrailerConsultDTO(UUID id, User user, Long movie, String url, LocalDate releaseDate, LocalDate uploadDate) {
		this.id = id;
		this.user = user;
		this.movie = movie;
		this.url = url;
		this.releaseDate = releaseDate;
		this.uploadDate = uploadDate;
	}

	protected UUID id;
	protected User user;
	protected Long movie;
	protected String url;
	protected LocalDate releaseDate;
	protected LocalDate uploadDate;
}
