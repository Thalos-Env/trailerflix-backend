package com.thalos.trailerflix.entities;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Data
@Entity(name = "tb_movie")
public class Movie {

	@Id
	private int id;

	@OneToMany(mappedBy = "movieId")
	private List<Trailer> trailersId;
}
