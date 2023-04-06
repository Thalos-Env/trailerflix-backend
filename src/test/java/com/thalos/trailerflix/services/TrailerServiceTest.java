package com.thalos.trailerflix.services;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.Optional;
import java.util.UUID;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;

import com.thalos.trailerflix.builder.TrailerBuilder;
import com.thalos.trailerflix.entities.Movie;
import com.thalos.trailerflix.entities.Trailer;
import com.thalos.trailerflix.entities.User;
import com.thalos.trailerflix.exceptions.ObjectNotFoundException;
import com.thalos.trailerflix.repositories.TrailerRepository;

@SpringBootTest
@ActiveProfiles("test")
public class TrailerServiceTest {

	@Autowired
	private TrailerService trailerService;
	
	@MockBean
	private TrailerRepository trailerRepository;

	private UUID id;
	
	private TrailerBuilder trailerBuilder;

	@BeforeEach
	public void setup() {
		id = UUID.randomUUID();
		setupTrailerBuilder();
	}
	
	public void setupTrailerBuilder() {
		trailerBuilder = new TrailerBuilder().withId(id).withUser(new User()).withMovie(new Movie()).withUrl("youtube.com")
											 .withReleaseDate(LocalDate.now()).withUploadDate(LocalDate.now());
	}

	@Test
	@DisplayName("Should show ObjectNotFoundException for non-existent trailer id")
	public void shouldShowObjectNotFoundExceptionTrailerId() {
		UUID newId = UUID.randomUUID();
		when(trailerRepository.findOneById(id)).thenReturn(Optional.of(trailerBuilder));

		ObjectNotFoundException exception = assertThrows(ObjectNotFoundException.class, () -> {
			trailerService.findTrailerById(newId);
		});

		assertEquals("Trailer não encontrado.", exception.getMessage());
	}

	@Test
	@DisplayName("Should find trailer by id")
	public void shouldFindTrailerById() {
		when(trailerRepository.findOneById(id)).thenReturn(Optional.of(trailerBuilder));

		Trailer result = trailerService.findTrailerById(id);

		assertThat(trailerBuilder).isSameAs(result);
	}
}
