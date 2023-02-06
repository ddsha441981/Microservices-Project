package com.cwc.hotel.service.services.impl;

import java.util.List;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cwc.hotel.service.entities.Hotel;
import com.cwc.hotel.service.exception.ResourceNotFoundException;
import com.cwc.hotel.service.repositories.HotelRepository;
import com.cwc.hotel.service.services.HotelService;

@Service
public class HotelServiceImpl implements HotelService{

	
	@Autowired
	private HotelRepository hotelRepository;
	
	 private Logger logger = LoggerFactory.getLogger(HotelServiceImpl.class);
	
	@Override
	public Hotel createHotel(Hotel hotel) {
		//Generate Random Hotel id
		String randomHotelId = UUID.randomUUID().toString();
		hotel.setHotelId(randomHotelId);
		Hotel savedHotel = hotelRepository.save(hotel);
		return savedHotel;
	}

	@Override
	public Hotel getSingleHotel(String hotelId) {
		Hotel hotel = hotelRepository.findById(hotelId).orElseThrow(()->new ResourceNotFoundException("Hotel with given id not found"));
		logger.info("{}",hotelId );
		return hotel;
		 
	}

	@Override
	public List<Hotel> getAllHotel() {
		List<Hotel> hotelList = hotelRepository.findAll();
		return hotelList;
	}

}
