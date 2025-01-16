package com.maspez.user_service.external.services;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

import com.maspez.user_service.entities.Rating;

@FeignClient(name = "RATING-SERVICE")
public interface RatingService {

	@PostMapping("/api/ratings/")
	public ResponseEntity<Rating> createRating(Rating rating);
	
	@PutMapping("/api/ratings/{ratingId}")
	public Rating updateRating(@PathVariable String ratingId, Rating rating);
	
	@DeleteMapping("/api/ratings/{ratingId}")
	public void deleteRating(@PathVariable String ratingId);
}
