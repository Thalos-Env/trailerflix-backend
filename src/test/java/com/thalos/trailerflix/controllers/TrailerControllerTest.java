package com.thalos.trailerflix.controllers;

import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.time.LocalDate;
import java.util.UUID;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.thalos.trailerflix.builder.TrailerBuilder;
import com.thalos.trailerflix.dtos.trailer.TrailerConsultDTO;
import com.thalos.trailerflix.dtos.trailer.TrailerMapper;
import com.thalos.trailerflix.entities.Movie;
import com.thalos.trailerflix.entities.User;
import com.thalos.trailerflix.services.TrailerService;

@SpringBootTest
@AutoConfigureMockMvc(addFilters = false)
@ActiveProfiles("test")
public class TrailerControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private TrailerService trailerServiceMock;

	private static final String REQUEST_MAPPING = "/api/trailers";
	private ObjectMapper objectMapper;
	private UUID id;

	private TrailerBuilder trailerBuilder;
	private TrailerConsultDTO trailerConsultDTO;

	@BeforeEach
	void setup() {
		id = UUID.randomUUID();
		setupTrailerBuilder();

		objectMapper = new ObjectMapper().registerModule(new JavaTimeModule());
		objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
	}

	public void setupTrailerBuilder() {
		trailerBuilder = new TrailerBuilder().withId(id).withUser(new User()).withMovie(new Movie()).withUrl("youtube.com")
											 .withReleaseDate(LocalDate.now()).withUploadDate(LocalDate.now());
	}

	@Test
	@DisplayName("Should find trailer by id and status 200")
	public void shouldFindTrailerByIdAndStatus200() throws Exception {
		when(trailerServiceMock.findTrailerById(eq(id))).thenReturn(trailerBuilder);
		
		trailerConsultDTO = TrailerMapper.fromEntity(trailerBuilder);
		
		String result = objectMapper.writeValueAsString(trailerConsultDTO);
		
		mockMvc.perform(get(REQUEST_MAPPING+ "/" + id)
				  .contentType(MediaType.APPLICATION_JSON))
				  .andExpect(status().isOk())
				  .andExpect(content().json(result));
	}	
}
