package com.thalos.trailerflix.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.thalos.trailerflix.dtos.MovieExternalApiDTO;
import com.thalos.trailerflix.services.MovieService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/movies")
@RequiredArgsConstructor
public class MovieController {

	private final MovieService movieService;

	@GetMapping("/{id}")
	public ResponseEntity<MovieExternalApiDTO> teste(@PathVariable Integer id) {
		MovieExternalApiDTO result = movieService.searchMoviesFromExternalApi(id);

		return new ResponseEntity<>(result, HttpStatus.OK);
	}
}
