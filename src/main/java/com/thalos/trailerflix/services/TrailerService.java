package com.thalos.trailerflix.services;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.thalos.trailerflix.entities.Trailer;
import com.thalos.trailerflix.exceptions.ObjectNotFoundException;
import com.thalos.trailerflix.repositories.TrailerRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TrailerService {

	private final TrailerRepository trailerRepository;

	public Trailer findTrailerById(UUID id) {
		Optional<Trailer> trailerFound = trailerRepository.findOneById(id);
		
		return trailerFound.orElseThrow(() -> new ObjectNotFoundException("Trailer não encontrado."));
	}

	public List<Trailer> findAllTrailers() {
	
		return trailerRepository.findAll();
	}
}
