package com.maspez.UserService;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.ResponseEntity;

import com.maspez.user_service.entities.Rating;
import com.maspez.user_service.external.services.RatingService;

@SpringBootTest
class UserServiceApplicationTest {
	
	@Autowired
	private RatingService ratingService;

//	@Test
//	void testCreateRating() {
//		Rating rating = Rating.builder().rating(10).userId("").hotelId("").feedback("This is create for test using feignClient").build();
//		ResponseEntity<Rating> response = ratingService.createRating(rating);
//		
//		System.out.println(response.getBody().toString());
//	}

}
