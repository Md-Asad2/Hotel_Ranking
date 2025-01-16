package com.maspez.hotel_service.controllers;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.maspez.hotel_service.entities.Hotel;
import com.maspez.hotel_service.services.HotelService;

@RestController
@RequestMapping("/api/hotels")
public class HotelController {

	private HotelService hotelService;

	public HotelController(HotelService hotelService) {
		this.hotelService = hotelService;
	}
	
	@PostMapping
	public ResponseEntity<Hotel> createHotel( @RequestBody Hotel hotel) {
		return ResponseEntity.status(HttpStatus.CREATED).body(hotelService.create(hotel));
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Hotel> getHotel( @PathVariable String id) {
		return ResponseEntity.ok(hotelService.getHotel(id));
	}
	
	@GetMapping
	public ResponseEntity<List<Hotel>> getHotels() {
		return ResponseEntity.ok(hotelService.getHotels());
	}
}
