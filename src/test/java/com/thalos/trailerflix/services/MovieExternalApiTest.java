package com.thalos.trailerflix.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@ActiveProfiles("test")
public class MovieExternalApiTest {

	@Autowired
	private MovieExternalApi movieExternalApi;

	@Test
	@DisplayName("Should show ObjectNotFoundException for non-existent id")
	public void showObjectNotFoundException() {
		
		Exception exception = assertThrows(Exception.class, () -> {
			movieExternalApi.searchMovieFromExternalApi((long)0);
		});

		assertEquals("Filme não encontrado.", exception.getMessage());
	}
}
