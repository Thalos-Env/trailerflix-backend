package com.thalos.trailerflix.dtos.external;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class MovieExternalApiDTO {

	protected Long id;
	protected boolean adult;
	protected String backdrop_path;
	protected List<GenreExternalApiDTO> genres;
	protected String original_language;
	protected String original_title;
	protected String overview;
	protected Double popularity;
	protected String poster_path;
	protected String release_date;
	protected String title;
	protected Double vote_average;
	protected int vote_count;
}
