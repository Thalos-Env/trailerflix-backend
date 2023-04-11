package com.thalos.trailerflix.controllers;

import com.thalos.trailerflix.config.security.TokenResponse;
import com.thalos.trailerflix.config.security.TokenService;
import com.thalos.trailerflix.dtos.ResetPasswordDTO;
import com.thalos.trailerflix.dtos.UserDTO;
import com.thalos.trailerflix.dtos.UserInsertDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.thalos.trailerflix.services.UserService;

import lombok.RequiredArgsConstructor;

import javax.mail.MessagingException;
import javax.validation.Valid;

@CrossOrigin
@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;
    private final TokenService tokenService;

    @GetMapping("/all")
    public ResponseEntity<Page<UserDTO>> findAll(Pageable pageRequest) {
        Page<UserDTO> userDTOPage = userService.findAll(pageRequest);
        return ResponseEntity.ok().body(userDTOPage);
    }

    @GetMapping("/login")
    public ResponseEntity<TokenResponse> signIn(@RequestParam String email, @RequestParam String senha) {
        TokenResponse tokenResponse = tokenService.generateToken(email, senha);
        return ResponseEntity.ok().body(tokenResponse);
    }

    @PostMapping("/cadastro")
    public ResponseEntity<UserDTO> signUp(@Valid @RequestBody UserInsertDTO userInsertDTO) throws MessagingException {
        UserDTO userDTO = userService.createUser(userInsertDTO);
        return ResponseEntity.ok().body(userDTO);
    }

    @PostMapping("/confirm-email/{email}")
    public ResponseEntity<?> confirmEmail(@RequestParam("email") String email) {
        userService.confirmEmail(email);
        return ResponseEntity.ok().body("E-mail confirmado com sucesso.");
    }

    @PostMapping("/resend/confirm-email/{email}")
    public ResponseEntity<?> resendConfirmEmail(@RequestParam("email") String email) throws MessagingException {
        userService.resendConfirmEmail(email);
        return ResponseEntity.ok().body("E-mail confirmado com sucesso.");
    }

    @PostMapping("/reset-password")
    public ResponseEntity<?> resetPassword(@RequestBody ResetPasswordDTO resetPasswordDTO) {
        userService.resetPassword(resetPasswordDTO);
        return ResponseEntity.ok().body("Senha alterada com sucesso.");
    }

    @PostMapping("/email/reset-password")
    public ResponseEntity<?> reset(@RequestBody String email) throws MessagingException {
        userService.sendEmailResetPassword(email);
        return ResponseEntity.ok().body("Email enviado neste endereço de email.");
    }
}
