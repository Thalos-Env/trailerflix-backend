package com.thalos.trailerflix.controllers;

import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

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
import com.thalos.trailerflix.builder.MovieExternalApiBuilder;
import com.thalos.trailerflix.dtos.external.MovieExternalApiDTO;
import com.thalos.trailerflix.dtos.trailer.TrailerMapper;
import com.thalos.trailerflix.services.MovieExternalApi;
import com.thalos.trailerflix.services.MovieService;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class MovieControllerTest {

	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	private MovieExternalApi movieExternalApi;

	private static final String REQUEST_MAPPING = "/api/movies";
	private ObjectMapper objectMapper;
	private Long id;

	@BeforeEach
	void setup() {
		id = (long)2304;

		objectMapper = new ObjectMapper().registerModule(new JavaTimeModule());
		objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
	}
	
	@Test
	@DisplayName("Should find external movie by id and status 200")
	public void shouldFindTrailerByIdAndStatus200() throws Exception {
		MovieExternalApiDTO movieExternalApiDTO = new MovieExternalApiDTO();
		movieExternalApiDTO.setId(id);
		
		when(movieExternalApi.searchMovieFromExternalApi(id)).thenReturn(movieExternalApiDTO);
		
		String result = objectMapper.writeValueAsString(movieExternalApiDTO);
		
		mockMvc.perform(get(REQUEST_MAPPING + "/external/" + id)
				  .contentType(MediaType.APPLICATION_JSON))
				  .andExpect(status().isOk())
				  .andExpect(content().json(result));
	}	
}
