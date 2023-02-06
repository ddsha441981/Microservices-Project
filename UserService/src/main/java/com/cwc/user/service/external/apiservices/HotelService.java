package com.cwc.user.service.external.apiservices;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import com.cwc.user.service.entities.Hotel;

@FeignClient(name = "HOTEL-SERVICE")
public interface HotelService {
	//call Hotel Service
	//@RequestMapping(method=RequestMethod.GET,value="/hotels/{hotelId}",consumes = "application/json")
	@GetMapping(path = "/hotels/{hotelId}",produces = "application/json")
	public Hotel getHotel(@PathVariable("hotelId") String hotelId);
}
