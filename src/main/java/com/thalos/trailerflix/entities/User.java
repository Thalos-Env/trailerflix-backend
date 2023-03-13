package com.thalos.trailerflix.entities;

import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
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
	private int id;
	
	private String profile;
	private String name;
	private String email;
	private Date registrationDate;
	
	@OneToMany(mappedBy = "collaborationId")
	private List<Trailer> trailersId;
}