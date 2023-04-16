package com.thalos.trailerflix.services;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.thalos.trailerflix.dtos.external.MovieExternalApiDTO;
import com.thalos.trailerflix.entities.Movie;
import com.thalos.trailerflix.exceptions.ObjectNotFoundException;
import com.thalos.trailerflix.repositories.MovieRepository;

import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class MovieService {

	private final MovieRepository movieRepository;
	private final WebClient webClient;

	public MovieExternalApiDTO searchMovieFromExternalApi(Long id) {
		Mono<MovieExternalApiDTO> monoMovie = 
				this.webClient
					.method(HttpMethod.GET)
					.uri(uriBuilder -> uriBuilder.path(id + "")
						.queryParam("language", "pt-BR")
						.queryParam("api_key", "3768983f3d84bc0b2dc209e8dcc24bd6")
						.build())
					.retrieve().bodyToMono(MovieExternalApiDTO.class)
					.onErrorResume(error -> Mono.empty());
		
		MovieExternalApiDTO movieFound = monoMovie.block();
		
		if(monoMovie.hasElement().block() == false) 
			throw new ObjectNotFoundException("Filme não encontrado.");

		return movieFound;
	}

	public boolean existsMovie(Long movieId) {
		Movie movieFound = movieRepository.findById(movieId).get();

		if (movieFound == null)
			return false;

		return true;
	}

	@Transactional
	public Movie saveMovie(Long movieId, Movie newMovie) {
		movieRepository.save(newMovie);
		return newMovie;
	}

	public Movie findMovieById(Long movieId) {
		Optional<Movie> movieFound = movieRepository.findById(movieId);

		return movieFound.orElseThrow(() -> new ObjectNotFoundException("Filme não encontrado."));
	}

	public Movie verifyMovie(Long movieId) {
		Movie resultMovie;

		if (this.existsMovie(movieId)) {
			Movie movieFound = findMovieById(movieId);
			resultMovie = movieFound;
			
		} else {
			this.searchMovieFromExternalApi(movieId);

			Movie newMovie = new Movie();
			newMovie.setId(movieId);
			resultMovie = this.saveMovie(movieId, newMovie);
		}

		return resultMovie;
	}
}
