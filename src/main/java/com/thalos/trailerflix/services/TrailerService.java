package com.thalos.trailerflix.services;

import org.springframework.stereotype.Service;

import com.thalos.trailerflix.repositories.TrailerRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TrailerService {

	private final TrailerRepository trailerRepository;
}
