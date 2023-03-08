package com.thalos.trailerflix.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.thalos.trailerflix.entities.Teste;
import com.thalos.trailerflix.services.TesteService;

@RestController
@RequestMapping("teste")
public class TesteController {

	private final TesteService testeService;

	public TesteController(TesteService testeService) {
		this.testeService = testeService;
	}

	@PostMapping("testando")
	public ResponseEntity<String> teste(@RequestBody Teste teste) {

		testeService.testando(teste);

		return ResponseEntity.status(HttpStatus.OK).body("Teste realizado com sucesso!");
	}
}
