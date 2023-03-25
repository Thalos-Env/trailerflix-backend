package com.thalos.trailerflix.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class MovieServiceTest {

	@Autowired
	MovieService movieService;
	
	Integer id;
	
	@BeforeEach
	public void setup() {
		
	}
	
	/*
	
	@Test
	@DisplayName("Deve ")
	public void teste() {
		id = 1000000000;
		
		Exception exception = assertThrows(Exception.class, () -> {
			movieService.searchMoviesFromExternalApi(id);
		});
		
		assertEquals("id not found", exception.getMessage());
	}
	
	*/
}
