package com.thalos.trailerflix.controllers;

import com.thalos.trailerflix.config.security.TokenResponse;
import com.thalos.trailerflix.config.security.TokenService;
import com.thalos.trailerflix.dtos.user.UserConsultDTO;
import com.thalos.trailerflix.dtos.user.UserRegisterDTO;
import com.thalos.trailerflix.dtos.user.UserMapper;
import com.thalos.trailerflix.entities.User;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.thalos.trailerflix.services.UserService;

import lombok.RequiredArgsConstructor;

import javax.validation.Valid;

@CrossOrigin
@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;
    private final TokenService tokenService;

    @GetMapping
    public ResponseEntity<Page<UserConsultDTO>> findAll(Pageable pageRequest) {
    	Page<User> user = userService.findAll(pageRequest);
    	Page<UserConsultDTO> userDTOPage = user.map(UserMapper::fromEntity);
    	
        return ResponseEntity.ok().body(userDTOPage);
    }

    @GetMapping("/login")
    public ResponseEntity<TokenResponse> signIn(@RequestParam String email, @RequestParam String senha) {
        TokenResponse tokenResponse = tokenService.generateToken(email, senha);
        return ResponseEntity.ok().body(tokenResponse);
    }

    @PostMapping("/register")
    public ResponseEntity<UserConsultDTO> signUp(@Valid @RequestBody UserRegisterDTO userRegisterDTO) {
    	User savedUser = userService.createUser(UserMapper.fromDTO(userRegisterDTO));
    	UserConsultDTO userDTO = UserMapper.fromEntity(savedUser);
    	
        return ResponseEntity.ok().body(userDTO);
    }
}
