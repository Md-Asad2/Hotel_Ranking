package com.maspez.rating_service.services;

import java.util.List;

import com.maspez.rating_service.entities.Rating;

public interface RatingService {

	Rating create(Rating rating);
	
	List<Rating> getRatings();
	
	List<Rating> getRatingsByUser(String userId);
	
	List<Rating> getRatingsByHotel(String hotelId);
}
