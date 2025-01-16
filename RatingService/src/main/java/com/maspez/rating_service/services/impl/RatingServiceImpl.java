package com.maspez.rating_service.services.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.maspez.rating_service.entities.Rating;
import com.maspez.rating_service.repositories.RatingRepostory;
import com.maspez.rating_service.services.RatingService;

@Service
public class RatingServiceImpl implements RatingService {
	
	private RatingRepostory ratingRepostory;

	public RatingServiceImpl(RatingRepostory ratingRepostory) {
		this.ratingRepostory = ratingRepostory;
	}

	@Override
	public Rating create(Rating rating) {
		return ratingRepostory.save(rating);
	}

	@Override
	public List<Rating> getRatings() {
		return ratingRepostory.findAll();
	}

	@Override
	public List<Rating> getRatingsByUser(String userId) {
		return ratingRepostory.findByUserId(userId);
	}

	@Override
	public List<Rating> getRatingsByHotel(String hotelId) {
		return ratingRepostory.findByHotelId(hotelId);
	}

}
