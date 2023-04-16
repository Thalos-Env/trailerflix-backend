package com.thalos.trailerflix.entities;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.Data;

@Data
@Entity(name = "tb_movie")
public class Movie {
	
	@Id
	private Long id;
	
	private String trailersId;
}
