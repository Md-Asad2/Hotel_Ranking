package com.maspez.rating_service.repositories;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.maspez.rating_service.entities.Rating;

public interface RatingRepostory extends MongoRepository<Rating, String> {
	
	List<Rating> findByUserId(String userId);
	
	List<Rating> findByHotelId(String hotelId);
}
