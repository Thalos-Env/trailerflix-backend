package com.thalos.trailerflix.dtos.trailer;

import java.time.LocalDate;
import java.util.UUID;

import com.thalos.trailerflix.entities.Movie;
import com.thalos.trailerflix.entities.User;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class TrailerRegisterDTO {
	
	private UUID id;
	private User userId;
	private Movie movieId;
	private String url;
	private LocalDate releaseDate;
	private LocalDate uploadDate;
	private LocalDate editDate;
}
