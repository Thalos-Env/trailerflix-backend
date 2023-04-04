package com.thalos.trailerflix.services;

import java.util.Optional;
import java.util.UUID;

import javax.transaction.Transactional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.thalos.trailerflix.dtos.UserDTO;
import com.thalos.trailerflix.dtos.UserInsertDTO;
import com.thalos.trailerflix.entities.User;
import com.thalos.trailerflix.exceptions.InternalServerException;
import com.thalos.trailerflix.exceptions.ObjectNotFoundException;
import com.thalos.trailerflix.repositories.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    public Page<UserDTO> findAll(Pageable pageable) {
        Page<User> userPage = userRepository.findAll(pageable);

        return userPage.map(UserDTO::new);
    }
    
    public User findById(UUID userId) {
    	Optional<User> userFound = userRepository.findById(userId);

    	return userFound.orElseThrow(() -> new ObjectNotFoundException("User não encontrado."));
    }

    public UserDTO createUser(UserInsertDTO userInsertDTO) {
        User userEmail = userRepository.findByEmail(userInsertDTO.getEmail());
        if (userEmail != null) throw new InternalServerException("Este email já está cadastrado");

        User user = new User();
        user.setId(UUID.randomUUID());
        user.setEmail(userInsertDTO.getEmail());
        user.setName(userInsertDTO.getName());
        user.setProfile(userInsertDTO.getProfile());
        user.setPassword(passwordEncoder.encode(userInsertDTO.getPassword()));

        user = userRepository.save(user);
        return new UserDTO(user);
    }
}
