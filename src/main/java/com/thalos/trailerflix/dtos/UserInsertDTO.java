package com.thalos.trailerflix.dtos;

import com.thalos.trailerflix.entities.User;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.time.LocalDate;
import java.util.UUID;

@Data
@RequiredArgsConstructor
public class UserInsertDTO {
    private UUID id;
    private String trailersId;

    @NotBlank
    private String profile;
    @NotBlank
    private String name;
    @NotBlank
    private String email;
    @NotBlank
    private String password;
    private LocalDate registrationDate;

    public UserInsertDTO(User user) {
        this.id = user.getId();
        this.trailersId = user.getTrailersId();
        this.profile = user.getProfile();
        this.name = user.getName();
        this.email = user.getEmail();
        this.password = user.getPassword();
        this.registrationDate = user.getRegistrationDate();
    }
}
