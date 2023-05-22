package com.thalos.trailerflix.services;

import java.util.Optional;
import java.util.UUID;

import javax.transaction.Transactional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

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

    public Page<User> findAll(Pageable pageable) {
        Page<User> userPage = userRepository.findAll(pageable);

        return userPage;
    }
    
    public User findById(UUID userId) {
    	Optional<User> userFound = userRepository.findById(userId);

    	return userFound.orElseThrow(() -> new ObjectNotFoundException("User não encontrado."));
    }
    
    public User findUserByEmail(User user) {
    	return userRepository.findByEmail(user.getEmail());
    }

	public User createUser(User user) {
		if (this.findUserByEmail(user) != null)
			throw new InternalServerException("Este email já está cadastrado");

		return userRepository.save(user);
	}
}
