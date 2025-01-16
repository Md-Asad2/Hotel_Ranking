package com.maspez.user_service.controllers;

import com.maspez.user_service.entities.User;
import com.maspez.user_service.services.UserService;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import io.github.resilience4j.retry.annotation.Retry;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {
	private Logger logger = LoggerFactory.getLogger(getClass());
	
    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody User user) {
        User createdUser = userService.saveUser(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdUser);
    }

    private int retryCount = 1;
    
    @GetMapping("/getByEmail")
//    @CircuitBreaker(name = "userRatingHotelBreaker", fallbackMethod = "userRatingHotelFallback")
//    @Retry(name = "ratingHotelService", fallbackMethod = "userRatingHotelFallback")
    @RateLimiter(name = "userRateLimiter", fallbackMethod = "userRatingHotelFallback")
    public ResponseEntity<User> getUserByEmail( @RequestParam String mailId) {
    	logger.info("Retry Count: {}", retryCount++);
        User user = userService.getUser(mailId);
        retryCount = 1;
        return ResponseEntity.ok(user);
    }
    
    public ResponseEntity<User> userRatingHotelFallback(String mailId, Exception ex) {
    	logger.info("Fallback is executed because service is down : " + ex.getMessage());
    	User user = User.builder()
    			.userId("0000001")
    			.email("dummy@gmail.com")
    			.name("Dummy")
    			.city("Unknown")
    			.build();
    	retryCount = 1;
    	return new ResponseEntity<>(user, HttpStatus.TOO_MANY_REQUESTS);
    }

    @GetMapping
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> users = userService.getAllUser();
        return ResponseEntity.ok(users);
    }

}
