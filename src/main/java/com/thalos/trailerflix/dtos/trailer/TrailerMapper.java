package com.thalos.trailerflix.dtos.trailer;

import java.time.LocalDate;
import java.util.UUID;

import com.thalos.trailerflix.entities.Movie;
import com.thalos.trailerflix.entities.Trailer;
import com.thalos.trailerflix.entities.User;

public class TrailerMapper {

	public static Trailer fromDTO(TrailerRegisterDTO dto, User user, Movie movie) {	
		return new Trailer(UUID.randomUUID(), user, movie, dto.getUrl(), dto.getReleaseDate(), dto.getUploadDate(), LocalDate.now());
	}
	
	public static TrailerConsultDTO fromEntity(Trailer trailer) {
		return new TrailerConsultDTO(trailer.getId(), trailer.getUser(), trailer.getMovie().getId(), trailer.getUrl(), trailer.getReleaseDate(), trailer.getUploadDate());
	}
}
