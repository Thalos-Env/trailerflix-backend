package com.thalos.trailerflix.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.thalos.trailerflix.entities.User;

@Repository
public interface UserRepository extends JpaRepository<User, UUID> {

}
