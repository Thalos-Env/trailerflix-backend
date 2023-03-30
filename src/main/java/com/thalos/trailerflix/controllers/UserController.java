package com.thalos.trailerflix.controllers;

import com.thalos.trailerflix.config.security.TokenResponse;
import com.thalos.trailerflix.config.security.TokenService;
import com.thalos.trailerflix.dtos.UserDTO;
import com.thalos.trailerflix.dtos.UserInsertDTO;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.thalos.trailerflix.services.UserService;

import lombok.RequiredArgsConstructor;

import javax.validation.Valid;
import java.awt.print.Pageable;

@CrossOrigin
@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {
	private final UserService userService;
	private TokenService tokenService;

	@GetMapping("/all")
	public ResponseEntity<Page<UserDTO>> findAll(Pageable pageable) {
		Page<UserDTO> userDTOPage = userService.findAll(pageable);
		return ResponseEntity.ok().body(userDTOPage);
	}

	@GetMapping("/login")
	public ResponseEntity<TokenResponse> signIn(@RequestParam String email, @RequestParam String senha) {
		TokenResponse tokenResponse = tokenService.generateToken(email, senha);
		return ResponseEntity.ok().body(tokenResponse);
	}

	@PostMapping("/cadastro")
	public ResponseEntity<UserDTO> signUp(@Valid @RequestBody UserInsertDTO userInsertDTO) {
		UserDTO userDTO = userService.createUser(userInsertDTO);
		return ResponseEntity.ok().body(userDTO);
	}
}
