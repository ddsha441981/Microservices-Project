package com.cwc.user.service.external.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.cwc.user.service.entities.Hotel;

@FeignClient(name = "HOTEL-SERVICE")
@Service
public interface HotelService {

	//call Hotel Service
	//@RequestMapping(method=RequestMethod.GET,value="/hotels/{hotelId}",consumes = "application/json")
	@GetMapping("/hotels/{hotelId}")
	public Hotel getHotel(@PathVariable("hotelId") String hotelId);
}
