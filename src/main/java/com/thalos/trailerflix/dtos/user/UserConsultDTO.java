package com.thalos.trailerflix.dtos.user;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class UserConsultDTO {
    private UUID id;
    private List<String> trailersId;
    private String profile;
    private String name;
    private LocalDate registrationDate;
    
	public UserConsultDTO(UUID id, List<String> trailersId, String profile, String name, LocalDate registrationDate) {
		this.id = id;
		this.trailersId = trailersId;
		this.profile = profile;
		this.name = name;
		this.registrationDate = registrationDate;
	}
}