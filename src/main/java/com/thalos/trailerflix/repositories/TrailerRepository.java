package com.thalos.trailerflix.repositories;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.thalos.trailerflix.entities.Trailer;

@Repository
public interface TrailerRepository extends JpaRepository<Trailer, UUID> {

	Optional<Trailer> findOneById(UUID id);
}
