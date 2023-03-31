package com.thalos.trailerflix.dtos.trailer;

import com.thalos.trailerflix.entities.Movie;
import com.thalos.trailerflix.entities.Trailer;
import com.thalos.trailerflix.entities.User;

public class TrailerMapper {

	public static Trailer fromDTO(TrailerRegisterDTO dto, User user, Movie movie) {				
		return new Trailer(null, user, movie, dto.getUrl(), dto.getReleaseDate(), dto.getUploadDate());
	}
	
	public static TrailerConsultDTO fromEntity(Trailer trailer) {
		return new TrailerConsultDTO(trailer.getId(), trailer.getUserId(), trailer.getMovieId(), trailer.getUrl(), trailer.getReleaseDate(), trailer.getUploadDate());
	}
}
