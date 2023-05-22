package com.thalos.trailerflix.config.security;

import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString
public class TokenResponse {
	
    private UUID id;
    private String token;
    private String timeToken;
    private String expirationDate;

    public TokenResponse(String token, String timeToken, String messageExpirationDate) {
        this.token = token;
        this.timeToken = timeToken;
        this.expirationDate = messageExpirationDate;
    }
}