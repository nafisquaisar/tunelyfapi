package com.tunelyf.nf.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tunelyf.nf.model.Genre;

@Repository
public interface GenreRepository extends JpaRepository<Genre, Long> {
//    Optional<Genre> findByName(String name);
//    Optional<Genre> findByNameIgnoreCase(String name);

}
