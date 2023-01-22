package com.cwc.hotel.service.services.impl;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cwc.hotel.service.entities.Hotel;
import com.cwc.hotel.service.exception.ResourceNotFoundException;
import com.cwc.hotel.service.repositories.HotelRepository;
import com.cwc.hotel.service.services.HotelService;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class HotelServiceImpl implements HotelService{

	@Autowired
	private HotelRepository hotelRepository;
	
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
		log.info("{}",hotelId );
		return hotel;
		 
	}

	@Override
	public List<Hotel> getAllHotel() {
		List<Hotel> hotelList = hotelRepository.findAll();
		return hotelList;
	}

}
