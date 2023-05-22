package com.thalos.trailerflix.controllers;

import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.thalos.trailerflix.dtos.trailer.TrailerConsultDTO;
import com.thalos.trailerflix.dtos.trailer.TrailerMapper;
import com.thalos.trailerflix.dtos.trailer.TrailerRegisterDTO;
import com.thalos.trailerflix.entities.Movie;
import com.thalos.trailerflix.entities.Trailer;
import com.thalos.trailerflix.entities.User;
import com.thalos.trailerflix.services.MovieService;
import com.thalos.trailerflix.services.TrailerService;
import com.thalos.trailerflix.services.UserService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/trailers")
@RequiredArgsConstructor
public class TrailerController {
	
	private final TrailerService trailerService;
	private final MovieService movieService;
	private final UserService userService;
	
	public Movie verifyMovieFromExternalApi(Long movieId) {
		return movieService.verifyMovie(movieId);
	}
	
	public User verifyUser(UUID userId) {
		return userService.findById(userId);
	}
	
	@PostMapping("/{movieId}")
	public ResponseEntity<TrailerConsultDTO> createTrailer(@RequestBody TrailerRegisterDTO trailerRegisterDTO, @PathVariable Long movieId) {
		Movie movieFound = this.verifyMovieFromExternalApi(movieId);
		User userFound = this.verifyUser(trailerRegisterDTO.getUser());
		
		Trailer convertedTrailer = TrailerMapper.fromDTO(trailerRegisterDTO, userFound, movieFound);
		Trailer savedTrailer = trailerService.createTrailer(convertedTrailer);
		TrailerConsultDTO result = TrailerMapper.fromEntity(savedTrailer);

		return new ResponseEntity<>(result, HttpStatus.CREATED);
	}

	@GetMapping("/{id}")
	public ResponseEntity<TrailerConsultDTO> findTrailerById(@PathVariable UUID id) {
		TrailerConsultDTO result = TrailerMapper.fromEntity(trailerService.findTrailerById(id));
		
		return new ResponseEntity<TrailerConsultDTO>(result, HttpStatus.OK);
	}
}
