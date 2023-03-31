package com.thalos.trailerflix.services;

import java.time.LocalDate;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.thalos.trailerflix.entities.Trailer;
import com.thalos.trailerflix.repositories.TrailerRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TrailerService {

	private final TrailerRepository trailerRepository;
	
	@Transactional
	public Trailer createTrailer(Trailer newTrailer) {
		newTrailer.setEditDate(LocalDate.now());
		
		Trailer trailerSaved = trailerRepository.save(newTrailer);
		
		return trailerSaved;
	}
}
