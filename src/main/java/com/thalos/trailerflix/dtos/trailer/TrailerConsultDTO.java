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

	private UUID id;
	private User user;
	private Long movie;
	private String url;
	private LocalDate releaseDate;
	private LocalDate uploadDate;
}
