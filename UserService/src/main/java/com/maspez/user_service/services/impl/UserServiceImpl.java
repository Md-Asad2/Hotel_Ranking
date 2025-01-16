package com.maspez.user_service.services.impl;

import com.maspez.user_service.constants.UserConstant;
import com.maspez.user_service.entities.Hotel;
import com.maspez.user_service.entities.Rating;
import com.maspez.user_service.entities.User;
import com.maspez.user_service.exceptions.ResourceNotFoundException;
import com.maspez.user_service.external.services.HotelService;
import com.maspez.user_service.repositories.UserRepository;

import com.maspez.user_service.services.UserService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

@Service
public class UserServiceImpl implements UserService {
	
	private Logger logger = LoggerFactory.getLogger(getClass());
	
	private RestTemplate restTemplate;

    private UserRepository userRepository;
    
    private HotelService hotelService;

    public UserServiceImpl(UserRepository userRepository, RestTemplate restTemplate, HotelService hotelService) {
        this.userRepository = userRepository;
        this.restTemplate = restTemplate;
        this.hotelService = hotelService;
    }

    @Override
    public User saveUser(User user) {
        String userId = UUID.randomUUID().toString();
        user.setUserId(userId);
        return userRepository.save(user);
    }

    @Override
    public List<User> getAllUser() {
        return userRepository.findAll();
    }

    @Override
    public User getUser(String mailId) {
        User user = userRepository.findByEmail(mailId)
                .orElseThrow(() -> new ResourceNotFoundException("User with given id not found on server!"));
        ResponseEntity<Rating[]> ratingsEntity = restTemplate.getForEntity("http://RATING-SERVICE/api/ratings/user/" + user.getUserId(), Rating[].class);
        logger.info("Response Status Code : {}", ratingsEntity.getStatusCode());
        
        if(ratingsEntity.getStatusCode().equals(HttpStatusCode.valueOf(UserConstant.HTTP_OK))) {
        	List<Rating> ratings = Arrays.asList(ratingsEntity.getBody());
            logger.info("Response ~~~~> {}", ratings);
            
        	ratings.forEach(rating -> {
        		ResponseEntity<Hotel> hotelEntity = hotelService.getHotel(rating.getHotelId());
        		logger.info("Response Status Code : {}", hotelEntity.getStatusCode());
                logger.info("Response ~~~~> {}", hotelEntity.getBody());
                
        		if(hotelEntity.getStatusCode().equals(HttpStatusCode.valueOf(UserConstant.HTTP_OK))) 
        			rating.setHotel(hotelEntity.getBody());
        	});
        	user.setRatings(ratings);
        }
        else 
        	user.setRatings(new ArrayList<>());
        
        return user;
    }

    @Override
    public User updateUser(User user) {
        return null;
    }

    @Override
    public void deleteUser(String userId) {
        userRepository.deleteById(userId);
    }
}
