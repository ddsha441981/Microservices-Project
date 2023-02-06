package com.cwc.user.service.external.apiservices;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

import com.cwc.user.service.entities.Rating;

@Service
@FeignClient(name = "RATING-SERVICE")
public interface RatingService {

	//Create Rating
	@PostMapping("/ratings")
	public ResponseEntity<Rating> createRating(Rating rating);
	//Update Rating
	@PutMapping("/ratings/{rating}")
	public  ResponseEntity<Rating> updateRating(@PathVariable("ratingId") String ratingId,Rating rating);
	//Get Rating
	@GetMapping("/ratings/{ratingId}")
	public  ResponseEntity<Rating> getSingleUserRating(@PathVariable("ratingId") String ratingId);
	//Delete Rating
	@DeleteMapping("/ratings/{ratingId}")
	public int deleteRating(@PathVariable("ratingId") String ratingId);
}
