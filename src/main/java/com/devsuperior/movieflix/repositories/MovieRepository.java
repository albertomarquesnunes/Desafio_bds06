package com.devsuperior.movieflix.repositories;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.devsuperior.movieflix.entities.Movie;

@Repository
public interface MovieRepository extends JpaRepository<Movie,Long>{
	
	@Query("Select obj from Movie obj  WHERE :genre in obj.genre ")
	Page<Movie> findInById(Pageable page);
	
	@Query("Select obj from Movie obj  WHERE :genreId = obj.genre.id ")
	Page<Movie> findByGenre(Long genreId, Pageable page);

	@Query("SELECT m FROM Movie m JOIN FETCH m.reviews WHERE m.id = :id ")
  	Optional<Movie> findByMovieId(Long id);
}
