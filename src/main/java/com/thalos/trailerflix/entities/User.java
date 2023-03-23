package com.thalos.trailerflix.entities;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Data
@Entity(name = "tb_user")
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private UUID id;
	
	@OneToMany(mappedBy = "collaboratorId", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<Trailer> trailerId;
	
	private String profile;
	private String name;
	private String email;
	private LocalDate registrationDate;
}