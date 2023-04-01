package com.thalos.trailerflix.config.security;

import com.thalos.trailerflix.dtos.UserDTO;
import lombok.*;

import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class TokenResponse {
    private UUID id;
//    private UserDTO userDTO;
    private String token;
    private String timeToken;
    private String expirationDate;

    public TokenResponse(String token, String timeToken, String messageExpirationDate) {
//        this.userDTO = userDTO;
        this.token = token;
        this.timeToken = timeToken;
        this.expirationDate = messageExpirationDate;
    }
}