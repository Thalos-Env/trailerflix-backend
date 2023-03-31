package com.thalos.trailerflix.entities;

import java.time.LocalDate;
import java.util.UUID;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Data
@Entity(name = "tb_user")
public class User {
	
	public User(UUID id, String profile, String name, String email, LocalDate registrationDate) {
		this.id = id;
		this.profile = profile;
		this.name = name;
		this.email = email;
		this.registrationDate = registrationDate;
	}
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private UUID id;	
	
	private String trailersId;
	private String profile;
	private String name;
	private String email;
	private LocalDate registrationDate;
}