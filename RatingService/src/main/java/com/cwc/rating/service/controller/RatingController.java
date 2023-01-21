package com.cwc.rating.service.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cwc.rating.service.entities.Rating;
import com.cwc.rating.service.services.RatingService;

@RestController
@RequestMapping("/ratings")
public class RatingController {

	@Autowired
	private RatingService ratingService;
	
	@PostMapping("/")
	public ResponseEntity<Rating> createRating(@RequestBody Rating rating) {
		Rating createdRating = ratingService.createRating(rating);
		return ResponseEntity.status(HttpStatus.CREATED).body(createdRating);
	}
	
	@GetMapping("/{ratingId}")
	public ResponseEntity<Rating> getSingleRstingById(@PathVariable String  ratingId){
		Rating singleRating = ratingService.getSingleRating(ratingId);
		return ResponseEntity.ok(singleRating);
	}
	
	@GetMapping("/")
	public ResponseEntity<List<Rating>> getAllRatingList(){
		List<Rating> allRatingList = ratingService.getAllRatingList();
		return ResponseEntity.ok(allRatingList);
	}
	
	@GetMapping("/users/{userId}")
	public ResponseEntity<List<Rating>> getRatingListByUser(@PathVariable String userId){
		List<Rating> userRatingList = ratingService.getRatingListByUser(userId);
		return ResponseEntity.ok(userRatingList);
	}
	
	@GetMapping("/hotels/{hotelId}")
	public ResponseEntity<List<Rating>> getRatingListByHotel(@PathVariable String hotelId){
		List<Rating> hotelRatingList = ratingService.getRatingByHotelId(hotelId);
		return ResponseEntity.ok(hotelRatingList);
	}
}
