package com.thalos.trailerflix.builder;

import java.time.LocalDate;
import java.util.UUID;

import com.thalos.trailerflix.entities.User;

public class UserBuilder extends User {
	private static final long serialVersionUID = 1L;

	public User build() {
		return new User(id, trailersId, profile, name, email, password, registrationDate);
	}

	public UserBuilder withId(UUID id) {
		this.id = id;
		return this;
	}
	
	public UserBuilder withTrailersId(String trailersId) {
		this.trailersId = trailersId;
		return this;
	}

	public UserBuilder withProfile(String profile) {
		this.profile = profile;
		return this;
	}
	
	public UserBuilder withName(String name) {
		this.name = name;
		return this;
	}
	
	public UserBuilder withEmail(String email) {
		this.email = email;
		return this;
	}
	
	public UserBuilder withPassword(String password) {
		this.password = password;
		return this;
	}
	
	public UserBuilder withRegistrationDate(LocalDate registrationDate) {
		this.registrationDate = registrationDate;
		return this;
	}
}
