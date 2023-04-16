package com.thalos.trailerflix.config.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin("*")
@RestController
@RequestMapping(value = "/oauth")
public class TokenController {
    @Autowired
    TokenService tokenService;

    @PostMapping("/post")
    public ResponseEntity<TokenResponse> signIn(@RequestParam String email, @RequestParam String senha) {
        TokenResponse tokenResponse = tokenService.generateToken(email, senha);
        return ResponseEntity.ok().body(tokenResponse);
    }
}
