package com.thalos.trailerflix.entities;

import java.time.LocalDate;
import java.util.UUID;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@RequiredArgsConstructor
@Data
@Entity(name = "tb_trailer")
@ToString(exclude = {"userId", "movieId"})
public class Trailer {

	public Trailer(UUID id, User userId, Movie movieId, String url, LocalDate releaseDate, LocalDate uploadDate, LocalDate editDate) {
		this.id = id;
		this.userId = userId;
		this.movieId = movieId;
		this.url = url;
		this.releaseDate = releaseDate;
		this.uploadDate = uploadDate;
		this.editDate = editDate;
	}
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private UUID id;

	@ManyToOne
	@JoinColumn(name = "user_id")
	private User userId;

	@ManyToOne
	@JoinColumn(name = "movie_id")
	private Movie movieId;

	private String url;
	private LocalDate releaseDate;
	private LocalDate uploadDate;
	private LocalDate editDate;
}
