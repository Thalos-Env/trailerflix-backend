package com.thalos.trailerflix.services;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
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
import com.thalos.trailerflix.builder.MovieExternalApiBuilder;
import com.thalos.trailerflix.dtos.external.MovieExternalApiDTO;
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
	
	@MockBean
	private MovieExternalApi movieExternalApi;
	
	private MovieBuilder movieBuilder;
	private Long id;

	@BeforeEach
	public void setup() {
		id = (long)100;
		this.setupMovieBuilder();
	}
	
	public void setupMovieBuilder() {
		movieBuilder = new MovieBuilder().withId(id);
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
	
	@Test
	@DisplayName("Should verify if movie exists in database")
	public void shouldVerifyIfMovieExistsInDatabase() {
		when(movieRepository.findById(id)).thenReturn(Optional.of(movieBuilder));

		Movie result = movieService.existsMovie(id);

		assertThat(movieBuilder).isSameAs(result);
	}
	
	@Test
	@DisplayName("Should return null when not found movie in database")
	public void showReturnNullWhenNotFoundMovieInDatabase() {
		when(movieRepository.findById(id)).thenReturn(Optional.of(movieBuilder));

		Movie result = movieService.existsMovie((long) 200);

		assertNull(result);
	}
	
	@Test
	@DisplayName("Should verify if movie will not be saved")
	public void shouldVerifyIfMovieWillNotBeSaved() {
		when(movieRepository.findById(id)).thenReturn(Optional.of(movieBuilder));

		Movie result = movieService.verifyMovie(id);

		assertThat(movieBuilder).isSameAs(result);
	}
	
	@Test 
	@DisplayName("Should verify if movie will be saved")
	public void shouldVerifyIfMovieWillBeSaved() {
		MovieExternalApiDTO movieAtExternalApi = new MovieExternalApiBuilder();
		movieAtExternalApi.setId(id);
		
		Movie movie = new Movie();
		movie.setId(movieAtExternalApi.getId());
		
		when(movieRepository.findById(id)).thenReturn(Optional.empty());
		when(movieExternalApi.searchMovieFromExternalApi(id)).thenReturn(movieAtExternalApi);
		when(movieRepository.save(movie)).thenReturn(movieBuilder);

		Movie result = movieService.verifyMovie(id);

		assertThat(movieBuilder).isSameAs(result);
	}
}
