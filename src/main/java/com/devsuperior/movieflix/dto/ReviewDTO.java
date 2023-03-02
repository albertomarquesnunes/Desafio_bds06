package com.devsuperior.movieflix.dto;

import java.io.Serializable;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.devsuperior.movieflix.entities.Review;
import com.devsuperior.movieflix.entities.User;

public class ReviewDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	
	private Long id;
	@NotBlank(message="Campo requerido")
	private String text;
	@NotNull(message="Campo requerido")
	private Long movieId;
	private User user;

	public ReviewDTO(Long id, String text, Long movieId,  User merda) {
		
		this.id = id;
		this.text = text;
		this.movieId = movieId;
		this.user = merda;
	}

	public ReviewDTO() {
	}

	public ReviewDTO(Long id, String text) {
		this.id = id;
		this.text = text;
	}
	
	public ReviewDTO(Review review) {
		id = review.getId();
		text = review.getText()	;
		movieId=review.getMovie().getId();
		user = review.getUser();
		
	}
	
	
	public Long getMovieId() {
		return movieId;
	}

	public void setMovieId(Long movieId) {
		this.movieId = movieId;
	}


	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public User getUser() {
		return user;
	}

	public void setUserId(User userId) {
		this.user = userId;
	}


	

}
