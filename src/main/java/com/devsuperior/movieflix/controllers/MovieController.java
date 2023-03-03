package com.devsuperior.movieflix.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.devsuperior.movieflix.dto.GenreDTO;
import com.devsuperior.movieflix.dto.MovieDTO;
import com.devsuperior.movieflix.dto.MovieReviewsDTO;
import com.devsuperior.movieflix.services.GenreService;
import com.devsuperior.movieflix.services.MovieService;
import com.devsuperior.movieflix.services.ReviewService;

@RestController
@RequestMapping(value = "/movies")
public class MovieController {

	@Autowired
	private MovieService service;
	@Autowired
	private GenreService genreService;
	@Autowired
	private ReviewService reviewService;
	
	@GetMapping
	public ResponseEntity<Page<MovieDTO>> findAll(@RequestParam(value="genreId", defaultValue="0") Long genreId, 
												@RequestParam(value="page",defaultValue = "0") Integer page,
												@RequestParam(value="linesPerPage",defaultValue = "12") Integer linesPerPage,
												@RequestParam(value="direction",defaultValue = "ASC") String direction,
												@RequestParam(value="orderBy",defaultValue = "title") String orderBy
												){
		PageRequest pageRequest = PageRequest.of(page, linesPerPage,Direction.valueOf(direction), orderBy);
	    if (genreId !=0) {
	    	
	    	Page<MovieDTO> dto = service.findByGenre(genreId,pageRequest);
	    	return ResponseEntity.ok().body(dto);
	    } else {
	    	Page<MovieDTO> dto = service.findAll(pageRequest);
	    	return ResponseEntity.ok().body(dto);
	    }

		
	}
	
	@GetMapping(value ="/{id}")
	public ResponseEntity<MovieDTO> findById(@PathVariable Long id) {
		MovieDTO dto = service.findById(id);
		return ResponseEntity.ok().body(dto);
	}
	
	@GetMapping(value ="/{id}/reviews")
	public ResponseEntity<MovieReviewsDTO> findByReviewId(@PathVariable Long id) {
		MovieReviewsDTO dto = reviewService.findReviewsByMovieId(id);
		return ResponseEntity.ok().body(dto);
	}
	
	@GetMapping(value ="/{id}?genres")
	public ResponseEntity<GenreDTO> findByGenresId(@PathVariable Long id) {
		GenreDTO dto = genreService.findById(id);
		return ResponseEntity.ok().body(dto);
	}
}
