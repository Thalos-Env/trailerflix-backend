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
	protected String backdropPath;
	protected List<GenreExternalApiDTO> genres;
	protected String originalLanguage;
	protected String originalTitle;
	protected String overview;
	protected Double popularity;
	protected String posterPath;
	protected String releaseDate;
	protected String title;
	protected Double voteAverage;
	protected int voteCount;
}
