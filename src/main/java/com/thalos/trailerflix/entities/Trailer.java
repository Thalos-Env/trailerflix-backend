package com.thalos.trailerflix.entities;

import java.util.Date;
import java.util.UUID;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Data
@Entity(name = "tb_trailer")
public class Trailer {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	
	//relacionamento
	private int movieId;
	
	//relacionamento
	private UUID userId;
	
	private String url;
	private Date releaseDate;
	private Date uploadDate;
	private Date editDate;
}
