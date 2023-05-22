package com.thalos.trailerflix.dtos.user;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.thalos.trailerflix.entities.User;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Data
public class UserMapper {    
	public static User fromDTO(UserRegisterDTO dto) {	
		//TODO: uuid should be genetared for db
		return new User(UUID.randomUUID(), null, dto.getProfile(), dto.getName(), dto.getEmail(), new BCryptPasswordEncoder().encode(dto.getPassword()), LocalDate.now());
	}

	public static UserConsultDTO fromEntity(User user) {
		return new UserConsultDTO(user.getId(), splitTrailers(user.getTrailersId()), user.getProfile(), user.getName(), user.getRegistrationDate());
	}
	
	private static List<String> splitTrailers(String trailersId) {
		if(trailersId != null) 
			return Arrays.asList(trailersId.split("#"));
		return null;
	}
}
