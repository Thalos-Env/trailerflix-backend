package com.thalos.trailerflix.builder;

import java.util.List;

import com.thalos.trailerflix.dtos.external.GenreExternalApiDTO;
import com.thalos.trailerflix.dtos.external.MovieExternalApiDTO;

public class MovieExternalApiBuilder extends MovieExternalApiDTO {

	public MovieExternalApiDTO build() {
		return new MovieExternalApiDTO(id, adult, backdrop_path, genres, original_language, original_title, overview,
				popularity, poster_path, release_date, title, vote_average, vote_count);
	}

	public MovieExternalApiBuilder withId(Long id) {
		this.id = id;
		return this;
	}

	public MovieExternalApiBuilder withAdult(boolean adult) {
		this.adult = adult;
		return this;
	}

	public MovieExternalApiBuilder withBackdropPath(String backdropPath) {
		this.backdrop_path = backdropPath;
		return this;
	}

	public MovieExternalApiBuilder withGenres(List<GenreExternalApiDTO> genres) {
		this.genres = genres;
		return this;
	}

	public MovieExternalApiBuilder withOriginalLanguage(String originalLanguage) {
		this.original_language = originalLanguage;
		return this;
	}

	public MovieExternalApiBuilder withOriginalTitle(String originalTitle) {
		this.original_title = originalTitle;
		return this;
	}

	public MovieExternalApiBuilder withOverview(String overview) {
		this.overview = overview;
		return this;
	}

	public MovieExternalApiBuilder withPopularity(Double popularity) {
		this.popularity = popularity;
		return this;
	}

	public MovieExternalApiBuilder withPosterPath(String posterPath) {
		this.poster_path = posterPath;
		return this;
	}

	public MovieExternalApiBuilder withReleaseDate(String releaseDate) {
		this.release_date = releaseDate;
		return this;
	}

	public MovieExternalApiBuilder withTitle(String title) {
		this.title = title;
		return this;
	}

	public MovieExternalApiBuilder withVoteAverage(Double voteAverage) {
		this.vote_average = voteAverage;
		return this;
	}

	public MovieExternalApiBuilder withVoteCount(int voteCount) {
		this.vote_count = voteCount;
		return this;
	}
}
