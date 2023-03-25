package com.thalos.trailerflix.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.thalos.trailerflix.services.TrailerService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/trailers")
@RequiredArgsConstructor
public class TrailerController {
	
	private final TrailerService trailerService;

}
