package com.thalos.trailerflix.controllers;

import java.time.LocalDate;
import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
import com.thalos.trailerflix.repositories.UserRepository;
import com.thalos.trailerflix.services.MovieService;
import com.thalos.trailerflix.services.TrailerService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/trailers")
@RequiredArgsConstructor
public class TrailerController {
	
	private final TrailerService trailerService;
	private final MovieService movieService;
	
	public Movie verifyMovieFromExternalApi(int movieId) {
		return movieService.createMovie(movieId);
	}
	
	private final UserRepository userRepository;
	public User verifyUser(UUID userId) {
		//TODO: substituir para quando estiver pronto o do user

		return userRepository.findAll().get(0);
	}
	
	@PostMapping("/{userId}/{movieId}")
	public ResponseEntity<TrailerConsultDTO> createTrailer(@RequestBody TrailerRegisterDTO trailerRegisterDTO, @PathVariable UUID userId, @PathVariable int movieId) {
		Movie movieFound = this.verifyMovieFromExternalApi(movieId);
		User userFound = this.verifyUser(userId);
		
		Trailer newTrailer = trailerService.createTrailer(TrailerMapper.fromDTO(trailerRegisterDTO, userFound, movieFound));
		
		TrailerConsultDTO result = TrailerMapper.fromEntity(newTrailer);

		return new ResponseEntity<>(result, HttpStatus.CREATED);
	}
/*
	@PutMapping("/{username}/todos/{id}")
	public ResponseEntity<Todo> modifyTodoById(@PathVariable String username, @RequestBody Todo todo, @PathVariable Long id) {
		
		
		Todo result = todoService.modifyTodoById(todo, id);

		return new ResponseEntity<>(result, HttpStatus.CREATED);
	}

	@GetMapping("/{username}/todos/{id}")
	public ResponseEntity<Todo> getTodoById(@PathVariable String username, @PathVariable Long id) {
		
		
		Todo result = todoService.getTodoById(id);

		return new ResponseEntity<>(result, HttpStatus.OK);
	}

	@GetMapping("/{username}/todos")
	public ResponseEntity<List<Todo>> getTodos(@PathVariable String username) {
		
		
		List<Todo> result = todoService.getTodos(username);

		return ResponseEntity.ok(result);
	}

	@DeleteMapping("/{username}/todos/{id}")
	public ResponseEntity<?> deleteTodoById(@PathVariable String username, @PathVariable Long id) {
		
		
		todoService.deleteTodoById(id);

		return new ResponseEntity<>(HttpStatus.OK);
	}*/

}
