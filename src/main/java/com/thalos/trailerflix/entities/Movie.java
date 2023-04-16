package com.thalos.trailerflix.entities;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Data
@Entity(name = "tb_movie")
public class Movie {
	
	public Movie(Long id, String trailersId) {
		this.id = id;
		this.trailersId = trailersId;
	}

	@Id
	protected Long id;
	
	protected String trailersId;
}
