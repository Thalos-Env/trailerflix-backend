package com.thalos.trailerflix.dtos.user;

import com.thalos.trailerflix.entities.User;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import javax.validation.constraints.NotBlank;

@Data
@RequiredArgsConstructor
public class UserRegisterDTO {
	
    @NotBlank 
    protected String profile;
    
    @NotBlank
    protected String name;
    
    @NotBlank
    protected String email;
    
    @NotBlank
    protected String password;	

	public UserRegisterDTO(User user) {
		this.profile = user.getProfile();
		this.name = user.getName();
		this.email = user.getEmail();
		this.password = user.getPassword();
	}
}
