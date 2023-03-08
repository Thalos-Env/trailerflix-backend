package com.thalos.trailerflix.services;

import org.springframework.stereotype.Service;

import com.thalos.trailerflix.entities.Teste;
import com.thalos.trailerflix.repositories.TesteRepository;

@Service
public class TesteService {

	private final TesteRepository testeRepository;
	
	public TesteService(TesteRepository testeRepository) {
		this.testeRepository = testeRepository;
	}
	
	public void testando(Teste teste) {
		testeRepository.save(teste);
	}
}
