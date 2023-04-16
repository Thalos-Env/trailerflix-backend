package com.thalos.trailerflix.builder;

import com.thalos.trailerflix.entities.Movie;

public class MovieBuilder extends Movie {

	public Movie build() {
		return new Movie(id, trailersId);
	}

	public MovieBuilder withId(Long id) {
		this.id = id;
		return this;
	}

	public MovieBuilder withTrailersId(String trailersId) {
		this.trailersId = trailersId;
		return this;
	}
}
