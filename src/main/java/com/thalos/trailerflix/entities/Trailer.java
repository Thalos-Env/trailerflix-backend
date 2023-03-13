package com.thalos.trailerflix.entities;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Data
@Entity(name = "tb_trailer")
public class Trailer {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;

	@ManyToOne
	@JoinColumn(name = "movie_id")
	private Movie movieId;

	@ManyToOne
	@JoinColumn(name = "collaboration_id")
	private Movie collaborationId;

	private String url;
	private Date releaseDate;
	private Date uploadDate;
	private Date editDate;
}
