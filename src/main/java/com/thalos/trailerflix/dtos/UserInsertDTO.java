package com.thalos.trailerflix.dtos;

import com.thalos.trailerflix.entities.User;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import javax.validation.constraints.NotBlank;

@Data
@RequiredArgsConstructor
public class UserInsertDTO {
    @NotBlank
    private String profile;
    @NotBlank
    private String name;
    @NotBlank
    private String email;
    @NotBlank
    private String password;

    public UserInsertDTO(User user) {
        this.profile = user.getProfile();
        this.name = user.getName();
        this.email = user.getEmail();
        this.password = user.getPassword();
    }
}
