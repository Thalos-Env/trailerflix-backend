package com.thalos.trailerflix.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.thalos.trailerflix.dtos.trailer.TrailerConsultDTO;
import com.thalos.trailerflix.dtos.trailer.TrailerMapper;
import com.thalos.trailerflix.entities.Trailer;
import com.thalos.trailerflix.services.TrailerService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/trailers")
@RequiredArgsConstructor
public class TrailerController {
	
	private final TrailerService trailerService;

	@GetMapping("/{id}")
	public ResponseEntity<TrailerConsultDTO> findTrailerById(@PathVariable UUID id) {
		TrailerConsultDTO result = TrailerMapper.fromEntity(trailerService.findTrailerById(id));
		
		return new ResponseEntity<TrailerConsultDTO>(result, HttpStatus.OK);
	}
	
	@GetMapping
	public ResponseEntity<List<TrailerConsultDTO>> findAllTrailers() {
		List<Trailer> trailers = trailerService.findAllTrailers();
		List<TrailerConsultDTO> result = new ArrayList<>();
		
		for(int i = 0; i < trailers.size(); i++) {
			result.add(TrailerMapper.fromEntity(trailers.get(i)));
		}
		
		return new ResponseEntity<>(result, HttpStatus.CREATED);
	}
}
