package com.devsuperior.movieflix.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.devsuperior.movieflix.entities.Movie;
import com.devsuperior.movieflix.entities.Review;
import com.devsuperior.movieflix.entities.User;

public class MovieReviewsDTO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private Long id;
	
	private String titulo;
	
	private List<Review> reviews = new ArrayList<>();
	
	public MovieReviewsDTO(Long id, String text, List<Review> reviews) {
		this.id = id;
		this.titulo = text;
		
		this.reviews = reviews;
	}
	
	public  MovieReviewsDTO (Movie x) {
		 
		
		this.id = x.getId();
		this.titulo=x.getTitle();
		this.reviews= x.getReviews();
		
	
	}
	


	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String text) {
		this.titulo = text;
	}


	public List<Review> getReviews() {
		return reviews;
	}

	public void setReviews(List<Review> reviews) {
		this.reviews = reviews;
	}
	

}
