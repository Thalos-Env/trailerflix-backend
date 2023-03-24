package com.thalos.trailerflix.entities;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Data
@Entity(name = "tb_movie")
public class Movie {

	@Id
	private int id;	
	
	private String trailersId;
}
