package com.cwc.hotel.service.services;

import java.util.List;

import com.cwc.hotel.service.entities.Hotel;

public interface HotelService {

	//create
	public Hotel createHotel(Hotel hotel);
	//get
	public Hotel getSingleHotel(String hotelId);
	//get all
	public List<Hotel> getAllHotel();
}
