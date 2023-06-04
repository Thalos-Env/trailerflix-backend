package com.thalos.trailerflix.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.thalos.trailerflix.dtos.external.MovieExternalApiDTO;
import com.thalos.trailerflix.services.MovieExternalApi;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/movies")
@RequiredArgsConstructor
public class MovieController {
	private final MovieExternalApi movieExternalApi;

	@GetMapping("/external/{id}")
	public ResponseEntity<MovieExternalApiDTO> searchMovieFromExternalApi(@PathVariable Long id) {
		MovieExternalApiDTO result = movieExternalApi.searchMovieFromExternalApi(id);

		return new ResponseEntity<>(result, HttpStatus.OK);
	}
	
	@GetMapping("/external/list/{idList}")
	public ResponseEntity<Object> getListFromExternalApi(@PathVariable Long idList) {
		Object result = movieExternalApi.getListFromExternalApi(idList);

		return new ResponseEntity<>(result, HttpStatus.OK);
	}
}
