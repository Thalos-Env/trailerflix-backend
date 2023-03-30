package com.thalos.trailerflix.services;

import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.thalos.trailerflix.dtos.external.MovieExternalApiDTO;
import com.thalos.trailerflix.exceptions.ObjectNotFoundException;
import com.thalos.trailerflix.repositories.MovieRepository;

import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class MovieService {

	private final MovieRepository movieRepository;
	private final WebClient webClient;

	public MovieExternalApiDTO searchMovieFromExternalApi(Integer id) {
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
		
		if(monoMovie.hasElement().block() == false) {
			throw new ObjectNotFoundException("Movie not found.");
		}

		return movieFound;
	}
}
