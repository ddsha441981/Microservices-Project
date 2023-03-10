package com.cwc.hotel.service.controller;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cwc.hotel.service.config.MapperConfig;
import com.cwc.hotel.service.entities.Hotel;
import com.cwc.hotel.service.services.HotelService;

@RestController
@RequestMapping("/hotels")
public class HotelController {

	private Logger logger = LogManager.getLogger(HotelController.class);
	
	@Autowired
	private HotelService hotelService;
	
	@PostMapping("/")
	public ResponseEntity<Hotel> createHotel(@RequestBody Hotel hotel){
		logger.info("HotelController : createHotel execution started..");
		logger.info("HotelController : createHotel request payload {} ", MapperConfig.mapToJsonString(hotel));
		Hotel createdHotel = hotelService.createHotel(hotel);
		return ResponseEntity.status(HttpStatus.CREATED).body(createdHotel);
	}
	
	@GetMapping("/{hotelId}")
	public ResponseEntity<Hotel> getSingleHotel(@PathVariable String hotelId){
		Hotel singleHotel = hotelService.getSingleHotel(hotelId);
		return ResponseEntity.status(HttpStatus.FOUND).body(singleHotel);
	}
	
	@GetMapping("/")
	public ResponseEntity<List<Hotel>> getAllHotels(){
		List<Hotel> allHotel = hotelService.getAllHotel();
		return ResponseEntity.ok(allHotel);
	}
}
