package com.thalos.trailerflix.controllers;

import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.thalos.trailerflix.builder.MovieExternalApiBuilder;
import com.thalos.trailerflix.services.MovieService;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class MovieControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private MovieService movieServiceMock;

	private static final String REQUEST_MAPPING = "/api/movies";
	private MovieExternalApiBuilder movieExternalApiBuilder;
	private ObjectMapper objectMapper;
	private int id;

	@BeforeEach
	void setup() {
		id = 1;

		objectMapper = new ObjectMapper().registerModule(new JavaTimeModule());
		objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
	}

	/* NÃO NECESSARIO
	@Test
	@DisplayName("Buscar avaliacao por Id deve retornar ConsultaAvaliacaoDTO e Status HTTP 200")
	public void buscarAvaliacaoPorIdDeveRetornarConsultaAvaliacaoDTO_Status200() throws Exception {
		when(movieServiceMock.searchMovieFromExternalApi(id)).thenReturn(movieExternalApiBuilder);
		
		String result = objectMapper.writeValueAsString(movieExternalApiBuilder);
		
		mockMvc.perform(get(REQUEST_MAPPING+ "/" + id)
				  .contentType(MediaType.APPLICATION_JSON))
				  .andExpect(status().isOk())
				  .andExpect(content().json(result));
	}*/

}
