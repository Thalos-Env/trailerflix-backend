package com.thalos.trailerflix.services;

import org.springframework.stereotype.Service;

import com.thalos.trailerflix.repositories.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService {

	private final UserRepository userRepository;
}
