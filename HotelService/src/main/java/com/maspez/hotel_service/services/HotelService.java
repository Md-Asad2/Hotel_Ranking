package com.maspez.hotel_service.services;

import java.util.List;

import com.maspez.hotel_service.entities.Hotel;

public interface HotelService {

	Hotel create(Hotel hotel);
	List<Hotel> getHotels();
	Hotel getHotel(String id);
}
