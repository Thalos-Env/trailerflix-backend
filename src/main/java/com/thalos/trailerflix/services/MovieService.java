package com.thalos.trailerflix.services;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.thalos.trailerflix.entities.Movie;
import com.thalos.trailerflix.exceptions.ObjectNotFoundException;
import com.thalos.trailerflix.repositories.MovieRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MovieService {

	private final MovieRepository movieRepository;
	private final MovieExternalApi movieExternalApi;

	public Movie existsMovie(Long movieId) {
		Optional<Movie> movieFound = movieRepository.findById(movieId);
		
		return movieFound.orElse(null);
	}

	@Transactional
	public Movie saveMovie(Movie newMovie) {
		return movieRepository.save(newMovie);
	}

	public Movie findMovieById(Long movieId) {
		Optional<Movie> movieFound = movieRepository.findById(movieId);

		return movieFound.orElseThrow(() -> new ObjectNotFoundException("Filme não encontrado."));
	}

	public Movie verifyMovie(Long movieId) {
		Movie resultMovie;

		if (this.existsMovie(movieId) !=  null) {
			Movie movieFound = findMovieById(movieId);
			resultMovie = movieFound;

		} else {
			movieExternalApi.searchMovieFromExternalApi(movieId);

			Movie newMovie = new Movie();
			newMovie.setId(movieId);
			resultMovie = this.saveMovie(newMovie);
		}

		return resultMovie;
	}
}
