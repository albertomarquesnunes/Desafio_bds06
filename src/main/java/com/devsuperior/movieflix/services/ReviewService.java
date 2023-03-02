package com.devsuperior.movieflix.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.devsuperior.movieflix.dto.ReviewDTO;
import com.devsuperior.movieflix.entities.Movie;
import com.devsuperior.movieflix.entities.Review;
import com.devsuperior.movieflix.entities.User;
import com.devsuperior.movieflix.repositories.ReviewRepository;
import com.devsuperior.movieflix.services.exceptions.ResourceNotFoundException;

@Service
public class ReviewService {

	
	@Autowired
	private ReviewRepository repository;
	
	@Autowired
	private AuthService authService;
	
	//@Transactional(readOnly = true)
	//public Page<ReviewDTO> findAll(Pageable pageable){
	//	Page<Review> page = repository.findAll(pageable);
	//	return page.map(x -> new ReviewDTO(x));
				
	//}
	@Transactional(readOnly = true)
	public List<ReviewDTO> findAll(){
		List<Review> list = repository.findAll();
		return list.stream().map(x -> new ReviewDTO(x)).collect(Collectors.toList());
	}
	
	@Transactional(readOnly = true)
	public ReviewDTO findById(Long id) {
		Optional<Review> obj = repository.findById(id);
		Review entity = obj.orElseThrow(()-> new ResourceNotFoundException("Não Encontrado"));
		return new ReviewDTO(entity);
		
	}
	@Transactional
	public  ReviewDTO insert(ReviewDTO dto) {
				
		Review entity = new Review();
			entity.setText(dto.getText()); 
			entity.setMovie(new Movie(dto.getMovieId(),null,null,null,null,null));
			entity.setUser(new User(authService.authenticated().getId(),authService.authenticated().getName(),authService.authenticated().getEmail(),null));
			entity = repository.save(entity);
			return new ReviewDTO(entity);
		
	}
	
}
