package com.thalos.trailerflix.controllers;

import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
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
import com.thalos.trailerflix.builder.MovieBuilder;
import com.thalos.trailerflix.builder.TrailerBuilder;
import com.thalos.trailerflix.builder.UserBuilder;
import com.thalos.trailerflix.dtos.trailer.TrailerConsultDTO;
import com.thalos.trailerflix.dtos.trailer.TrailerMapper;
import com.thalos.trailerflix.dtos.trailer.TrailerRegisterDTO;
import com.thalos.trailerflix.entities.Trailer;
import com.thalos.trailerflix.services.MovieService;
import com.thalos.trailerflix.services.TrailerService;
import com.thalos.trailerflix.services.UserService;

@SpringBootTest
@AutoConfigureMockMvc(addFilters = false)
@ActiveProfiles("test")
public class TrailerControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private TrailerService trailerServiceMock;
	
	@MockBean
	private MovieService movieServiceMock;
	
	@MockBean
	private UserService userServiceMock;
	
	@MockBean
	private TrailerMapper mapper;

	private static final String REQUEST_MAPPING = "/api/trailers";
	private ObjectMapper objectMapper;
	private LocalDate todayDate = LocalDate.now();
	private UUID id;

	private TrailerBuilder trailerBuilder;
	private MovieBuilder movieBuilder;
	private UserBuilder userBuilder;
	
	private TrailerConsultDTO trailerConsultDTO;
	private TrailerRegisterDTO trailerRegisterDTO;

	@BeforeEach
	void setup() {
		id = UUID.randomUUID();
		this.setupMovieBuilder();
		this.setupUserBuilder();
		this.setupTrailerBuilder();
		this.setupTrailerRegisterDTO();

		objectMapper = new ObjectMapper().registerModule(new JavaTimeModule());
		objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
	}

	public void setupTrailerBuilder() {
		trailerBuilder = new TrailerBuilder().withId(id).withUser(userBuilder).withMovie(movieBuilder).withUrl("youtube.com")
											 .withReleaseDate(todayDate).withUploadDate(todayDate).withEditDate(todayDate);
	}
	
	public void setupMovieBuilder() {
		movieBuilder = new MovieBuilder().withId((long)100);
	}
	
	public void setupUserBuilder() {
		userBuilder = new UserBuilder().withId(id).withEmail("teste@gmail.com").withName("Teste").withProfile("user").withRegistrationDate(todayDate);
	}
	
	public void setupTrailerRegisterDTO() {
		trailerRegisterDTO = new TrailerRegisterDTO(id, "teste", todayDate);
	}

	@Test
	@DisplayName("Should find trailer by id and status 200")
	public void shouldFindTrailerByIdAndStatus200() throws Exception {
		when(trailerServiceMock.findTrailerById(eq(id))).thenReturn(trailerBuilder);
		
		trailerConsultDTO = TrailerMapper.fromEntity(trailerBuilder);
		
		String result = objectMapper.writeValueAsString(trailerConsultDTO);
		
		mockMvc.perform(get(REQUEST_MAPPING + "/" + id)
				  .contentType(MediaType.APPLICATION_JSON))
				  .andExpect(status().isOk())
				  .andExpect(content().json(result));
	}	
	
	@Test
	@DisplayName("Should create trailer and status 200")
	public void shouldCreateTrailerAndStatus200() throws Exception {
		UUID userId = trailerRegisterDTO.getUser();
		userBuilder.setId(userId);
		
		Trailer trailer = new Trailer(null, userBuilder, movieBuilder, "teste", todayDate, todayDate, todayDate);
		
		when(movieServiceMock.verifyMovie(movieBuilder.getId())).thenReturn(movieBuilder);
		when(userServiceMock.findById(userId)).thenReturn(userBuilder);
		when(trailerServiceMock.createTrailer(trailer)).thenReturn(trailerBuilder);

		String register = objectMapper.writeValueAsString(trailerRegisterDTO);
		
		trailerConsultDTO = TrailerMapper.fromEntity(trailerBuilder);
		String result = objectMapper.writeValueAsString(trailerConsultDTO);

		mockMvc.perform(post(REQUEST_MAPPING + "/" + movieBuilder.getId())
				  .content(register)
				  .contentType(MediaType.APPLICATION_JSON))
				  .andExpect(status().isCreated())
				  .andExpect(content().json(result));
	}	
}
