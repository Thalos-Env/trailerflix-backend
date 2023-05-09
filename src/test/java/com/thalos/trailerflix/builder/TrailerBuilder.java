package com.thalos.trailerflix.builder;

import java.time.LocalDate;
import java.util.UUID;

import com.thalos.trailerflix.entities.Movie;
import com.thalos.trailerflix.entities.Trailer;
import com.thalos.trailerflix.entities.User;

public class TrailerBuilder extends Trailer {

	public Trailer build() {
		return new Trailer(id, user, movie, url, releaseDate, uploadDate, editDate);
	}

	public TrailerBuilder withId(UUID id) {
		this.id = id;
		return this;
	}

	public TrailerBuilder withUser(User user) {
		this.user = user;
		return this;
	}

	public TrailerBuilder withMovie(Movie movie) {
		this.movie = movie;
		return this;
	}

	public TrailerBuilder withUrl(String url) {
		this.url = url;
		return this;
	}

	public TrailerBuilder withReleaseDate(LocalDate releaseDate) {
		this.releaseDate = releaseDate;
		return this;
	}

	public TrailerBuilder withUploadDate(LocalDate uploadDate) {
		this.uploadDate = uploadDate;
		return this;
	}
	
	public TrailerBuilder withEditDate(LocalDate editDate) {
		this.editDate = editDate;
		return this;
	}
}
