package com.cwc.user.service.external.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

import com.cwc.user.service.entities.Rating;

@FeignClient(name = "RATING-SERVICE")
@Service
public interface RatingService {

	//Create Rating
	@PostMapping("/ratings")
	public Rating createRating(Rating rating);
	//Update Rating
	@PutMapping("/ratings/{rating}")
	public Rating updateRating(@PathVariable("ratingId") String ratingId,Rating rating);
	//Get Rating
	@GetMapping("/ratings/{ratingId}")
	public Rating getSingleUserRating(@PathVariable("ratingId") String ratingId);
	//Delete Rating
	@DeleteMapping("/ratings/{ratingId}")
	public int deleteRating(@PathVariable("ratingId") String ratingId);
}
