package com.thalos.trailerflix.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

import java.util.UUID;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.thalos.trailerflix.exceptions.ObjectNotFoundException;
import com.thalos.trailerflix.repositories.UserRepository;

@SpringBootTest
public class UserServiceTest {
	
	@MockBean
	private UserRepository userRepository;

	@Autowired
	private UserService userService;

	private UUID userId;

	@BeforeEach
	public void setup() {
		userId = UUID.randomUUID();
	}

	@Test
	@DisplayName("Should show ObjectNotFoundException for non-existent id")
	public void showObjectNotFoundException() {
		when(userRepository.findById(userId)).thenReturn(null);

		ObjectNotFoundException exception = assertThrows(ObjectNotFoundException.class, () -> {
			userService.findById(userId);
		});

		assertEquals("User não encontrado.", exception.getMessage());
	}
	
	
}
