package com.thalos.trailerflix.builder;

import java.util.List;

import com.thalos.trailerflix.dtos.external.GenreExternalApiDTO;
import com.thalos.trailerflix.dtos.external.MovieExternalApiDTO;

public class MovieExternalApiBuilder extends MovieExternalApiDTO {

	public MovieExternalApiDTO build() {
		return new MovieExternalApiDTO(id, adult, backdropPath, genres, originalLanguage, originalTitle, overview,
				popularity, posterPath, releaseDate, title, voteAverage, voteCount);
	}

	public MovieExternalApiBuilder withId(int id) {
		this.id = id;
		return this;
	}

	public MovieExternalApiBuilder withAdult(boolean adult) {
		this.adult = adult;
		return this;
	}

	public MovieExternalApiBuilder withBackdropPath(String backdropPath) {
		this.backdropPath = backdropPath;
		return this;
	}

	public MovieExternalApiBuilder withGenres(List<GenreExternalApiDTO> genres) {
		this.genres = genres;
		return this;
	}

	public MovieExternalApiBuilder withOriginalLanguage(String originalLanguage) {
		this.originalLanguage = originalLanguage;
		return this;
	}

	public MovieExternalApiBuilder withOriginalTitle(String originalTitle) {
		this.originalTitle = originalTitle;
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
		this.posterPath = posterPath;
		return this;
	}

	public MovieExternalApiBuilder withReleaseDate(String releaseDate) {
		this.releaseDate = releaseDate;
		return this;
	}

	public MovieExternalApiBuilder withTitle(String title) {
		this.title = title;
		return this;
	}

	public MovieExternalApiBuilder withVoteAverage(Double voteAverage) {
		this.voteAverage = voteAverage;
		return this;
	}

	public MovieExternalApiBuilder withVoteCount(int voteCount) {
		this.voteCount = voteCount;
		return this;
	}
}
