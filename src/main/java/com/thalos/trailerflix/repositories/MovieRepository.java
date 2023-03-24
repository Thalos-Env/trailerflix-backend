package com.thalos.trailerflix.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.thalos.trailerflix.entities.Movie;

@Repository
public interface MovieRepository extends JpaRepository<Movie, Long> {

}
