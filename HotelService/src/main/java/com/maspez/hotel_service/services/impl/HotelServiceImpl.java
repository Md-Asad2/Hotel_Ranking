package com.maspez.hotel_service.services.impl;

import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.maspez.hotel_service.entities.Hotel;
import com.maspez.hotel_service.exceptions.ResourceNotFoundException;
import com.maspez.hotel_service.repositories.HotelRepository;
import com.maspez.hotel_service.services.HotelService;

@Service
public class HotelServiceImpl implements HotelService {
	
	private HotelRepository hotelRepository;
	
	public HotelServiceImpl(HotelRepository hotelRepository) {
		this.hotelRepository = hotelRepository;
	}

	@Override
	public Hotel create(Hotel hotel) {
		String id = UUID.randomUUID().toString();
		hotel.setId(id);
		
		return hotelRepository.save(hotel);
	}

	@Override
	public List<Hotel> getHotels() {
		return hotelRepository.findAll();
	}

	@Override
	public Hotel getHotel(String id) {
		return hotelRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Hotel with given id not found!"));
	}

}
