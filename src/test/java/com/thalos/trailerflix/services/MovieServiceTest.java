package com.thalos.trailerflix.services;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;

import com.thalos.trailerflix.builder.MovieBuilder;
import com.thalos.trailerflix.entities.Movie;
import com.thalos.trailerflix.exceptions.ObjectNotFoundException;
import com.thalos.trailerflix.repositories.MovieRepository;


@SpringBootTest
@ActiveProfiles("test")
public class MovieServiceTest {

	@Autowired
	private MovieService movieService;
	
	@MockBean
	private MovieRepository movieRepository;
	
	private MovieBuilder movieBuilder;
	private Long id;

	@BeforeEach
	public void setup() {
		id = (long)100;
		setupMovieBuilder();
	}
	
	public void setupMovieBuilder() {
		movieBuilder = new MovieBuilder().withId(id);
	}

	@Test
	@DisplayName("Should show ObjectNotFoundException for non-existent id")
	public void showObjectNotFoundException() {
		id = (long)1000000000;

		ObjectNotFoundException exception = assertThrows(ObjectNotFoundException.class, () -> {
			movieService.searchMovieFromExternalApi(id);
		});

		assertEquals("Filme não encontrado.", exception.getMessage());
	}
	
	@Test
	@DisplayName("Should find movie by id")
	public void shouldFindMovieById() {
		when(movieRepository.findById(id)).thenReturn(Optional.of(movieBuilder));
		
		Movie result = movieService.findMovieById(id);
		
		assertThat(movieBuilder).isSameAs(result);
	}
	
	@Test
	@DisplayName("Should show ObjectNotFoundException for not found movie id")
	public void showObjectNotFoundExceptionNotFoundMovieId() {
		when(movieRepository.findById(id)).thenReturn(Optional.of(movieBuilder));

		ObjectNotFoundException exception = assertThrows(ObjectNotFoundException.class, () -> {
			movieService.findMovieById((long)200);
		});

		assertEquals("Filme não encontrado.", exception.getMessage());
	}
	
	@Test
	@DisplayName("Should create movie")
	public void shouldCreateMovie() {
		when(movieRepository.save(movieBuilder)).thenReturn(movieBuilder);
		
		Movie result = movieService.saveMovie(movieBuilder);
		
		assertThat(movieBuilder).isSameAs(result);
	}
}
