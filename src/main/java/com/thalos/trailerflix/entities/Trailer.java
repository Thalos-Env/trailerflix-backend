package com.thalos.trailerflix.entities;

import java.time.LocalDate;
import java.util.UUID;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.Type;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Data
@Entity(name = "tb_trailer")
public class Trailer {

	public Trailer(UUID id, User user, Movie movie, String url, LocalDate releaseDate, LocalDate uploadDate, LocalDate editDate) {
		this.id = id;
		this.user = user;
		this.movie = movie;
		this.url = url;
		this.releaseDate = releaseDate;
		this.uploadDate = uploadDate;
		this.editDate = editDate;
	}
	
	@Id
	@Type(type = "org.hibernate.type.UUIDCharType")
	private UUID id;

	@ManyToOne
	@JoinColumn(name = "user")
	private User user;

	@ManyToOne
	@JoinColumn(name = "movie")
	private Movie movie;

	private String url;
	private LocalDate releaseDate;
	private LocalDate uploadDate;
	private LocalDate editDate;
}
