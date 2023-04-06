package com.thalos.trailerflix.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import com.thalos.trailerflix.exceptions.ObjectNotFoundException;

@SpringBootTest
@ActiveProfiles("test")
public class MovieServiceTest {

	@Autowired
	private MovieService movieService;

	private int id;

	@BeforeEach
	public void setup() {

	}

	@Test
	@DisplayName("Should show ObjectNotFoundException for non-existent id")
	public void showObjectNotFoundException() {
		id = 1000000000;

		ObjectNotFoundException exception = assertThrows(ObjectNotFoundException.class, () -> {
			movieService.searchMovieFromExternalApi(id);
		});

		assertEquals("Movie not found.", exception.getMessage());
	}
}
