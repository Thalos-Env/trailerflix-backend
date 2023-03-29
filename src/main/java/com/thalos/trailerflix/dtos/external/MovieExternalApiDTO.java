package com.thalos.trailerflix.dtos.external;

import java.util.List;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Data
public class MovieExternalApiDTO {

	private boolean adult;
	private String backdrop_path;
	private List<GenreExternalApiDTO> genres;
	private int id;
	private String original_language;
	private String original_title;
	private String overview;
	private Double popularity;
	private String poster_path;
	private String release_date;
	private String title;
	private Double vote_average;
	private int vote_count;
}
