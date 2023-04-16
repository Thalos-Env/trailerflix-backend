package com.thalos.trailerflix.services;

import java.util.Optional;
import java.util.UUID;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.thalos.trailerflix.entities.Trailer;
import com.thalos.trailerflix.exceptions.ObjectNotFoundException;
import com.thalos.trailerflix.repositories.TrailerRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TrailerService {

	private final TrailerRepository trailerRepository;
	
	@Transactional
	public Trailer createTrailer(Trailer newTrailer) {		
		Trailer trailerSaved = trailerRepository.save(newTrailer);
		
		return trailerSaved;
	}

	public Trailer findTrailerById(UUID id) {
		Optional<Trailer> trailerFound = trailerRepository.findOneById(id);
		
		return trailerFound.orElseThrow(() -> new ObjectNotFoundException("Trailer não encontrado."));
	}

}
