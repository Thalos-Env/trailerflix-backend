package com.thalos.trailerflix.dtos;

import com.thalos.trailerflix.entities.User;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.time.LocalDate;
import java.util.UUID;

@Data
@RequiredArgsConstructor
public class UserDTO {
    private UUID id;
    private String trailersId;
    private String profile;
    private String name;
    private String email;
    private LocalDate registrationDate;

    public UserDTO(User user) {
        this.id = user.getId();
        this.trailersId = user.getTrailersId();
        this.profile = user.getProfile();
        this.name = user.getName();
        this.email = user.getEmail();
        this.registrationDate = user.getRegistrationDate();
    }
}