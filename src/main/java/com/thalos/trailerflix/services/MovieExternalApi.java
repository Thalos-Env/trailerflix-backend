package com.thalos.trailerflix.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.thalos.trailerflix.dtos.external.MovieExternalApiDTO;
import com.thalos.trailerflix.dtos.external.ResultsExternalApiDTO;
import com.thalos.trailerflix.exceptions.ObjectNotFoundException;

import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class MovieExternalApi {

	private final WebClient webClient;
	
	@Value("${api-key}")
	private String apikey;
	
	public MovieExternalApiDTO searchMovieFromExternalApi(Long id) {
		Mono<MovieExternalApiDTO> monoMovie = 
				this.webClient
					.method(HttpMethod.GET)
					.uri(uriBuilder -> uriBuilder.path("movie/" + id.toString())
						.queryParam("language", "pt-BR")
						.queryParam("api_key", apikey)
						.build())
					.retrieve().bodyToMono(MovieExternalApiDTO.class)
					.onErrorResume(error -> Mono.empty());
		
		MovieExternalApiDTO movieFound = monoMovie.block();
		
		if(monoMovie.hasElement().block() == false) 
			throw new ObjectNotFoundException("Filme não encontrado.");

		return movieFound;
	}
	
	public ResultsExternalApiDTO getListFromExternalApi(Long id, String param, String path) {
		Mono<ResultsExternalApiDTO> monoMovie = 	
				this.webClient
						.method(HttpMethod.GET)
						.uri(uriBuilder -> uriBuilder.path(path)
							.queryParamIfPresent(param, Optional.of(id))
							.queryParam("language", "pt-BR")
							.queryParam("api_key", apikey)
							.build())
						.retrieve().bodyToMono(ResultsExternalApiDTO.class)
						.onErrorResume(error -> Mono.empty());
		
		ResultsExternalApiDTO movieFound = monoMovie.block();
		
		if(monoMovie.hasElement().block() == false) 
			throw new ObjectNotFoundException("Filme não encontrado.");

		return movieFound;
	}

	public Object getListFromExternalApi(Long idList) {
		String param = "";
		String path = null;
		
		switch (idList.toString()) {
			case "0": {
				param = "with_network";
				path = "discover/tv";
				return this.getListFromExternalApi((long) 213, param, path);
			}
	
			case "1": {
				path = "trending/all/week";
				return this.getListFromExternalApi(idList, param, path);
			}
	
			case "2": {
				path = "movie/top_rated";
				return this.getListFromExternalApi(idList, param, path);
			}
			
			default: {
				param = "with_genres";
				path = "discover/movie";
				return this.getListFromExternalApi(idList, param, path);
			}
		}
	}
}
