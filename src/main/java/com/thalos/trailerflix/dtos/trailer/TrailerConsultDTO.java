package com.thalos.trailerflix.dtos.trailer;

import java.time.LocalDate;
import java.util.UUID;

import com.thalos.trailerflix.entities.Movie;
import com.thalos.trailerflix.entities.User;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class TrailerConsultDTO {

	public TrailerConsultDTO(UUID id, User userId, Movie movieId, String url, LocalDate releaseDate, LocalDate uploadDate) {
		this.id = id;
		this.userId = userId;
		this.movieId = movieId;
		this.url = url;
		this.releaseDate = releaseDate;
		this.uploadDate = uploadDate;
	}
	
	private UUID id;
	private User userId;
	private Movie movieId;
	private String url;
	private LocalDate releaseDate;
	private LocalDate uploadDate;
}
