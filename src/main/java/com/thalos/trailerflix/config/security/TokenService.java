package com.thalos.trailerflix.config.security;

import com.thalos.trailerflix.dtos.UserDTO;
import com.thalos.trailerflix.entities.User;
import com.thalos.trailerflix.exceptions.ObjectNotFoundException;
import com.thalos.trailerflix.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.Arrays;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TokenService {
    private JwtEncoder jwtEncoder;
    private UserRepository userRepository;
    private BCryptPasswordEncoder passwordEncoder;
    public TokenResponse generateToken(String email, String senha) {
        Instant instantNow = Instant.now();
        User user = userRepository.findByEmail(email);
        if (user == null) {
            throw new ObjectNotFoundException("Este e-mail não está cadastrado.");
        }
        if (!passwordEncoder.matches(senha, user.getPassword())) {
            throw new ObjectNotFoundException("Senha inválida.");
        }
        String scope = user.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.joining(" "));
        JwtClaimsSet claims = JwtClaimsSet.builder()
                .issuer("self")
                .issuedAt(instantNow.atZone(ZoneId.systemDefault()).toInstant())
                .expiresAt(instantNow.atZone(ZoneId.systemDefault()).toInstant().plus(24, ChronoUnit.HOURS))
                .subject(user.getEmail())
                .claim("scope", scope)
                .build();
        String token = jwtEncoder.encode(JwtEncoderParameters.from(claims)).getTokenValue();

        Instant expirationDate = jwtEncoder.encode(JwtEncoderParameters.from(claims)).getExpiresAt();
        String timeToken = "24:00:00 hours";

        String[] splitExpirationDate = String.valueOf(expirationDate).split("T");
        String messageExpirationDate = splitExpirationDate[0] + " " + Arrays.toString(splitExpirationDate[1].split("\\."));

        UserDTO userDTO = new UserDTO(user);
        return new TokenResponse(user.getId(), token, timeToken, messageExpirationDate);
    }
}
