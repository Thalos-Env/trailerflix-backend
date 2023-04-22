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
					.uri(uriBuilder -> uriBuilder.path(id.toString())
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
			this.searchMovieFromExternalApi(movieId);

			Movie newMovie = new Movie();
			newMovie.setId(movieId);
			resultMovie = this.saveMovie(newMovie);
		}

		return resultMovie;
	}
}
