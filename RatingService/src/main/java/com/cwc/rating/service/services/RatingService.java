package com.cwc.rating.service.services;

import java.util.List;

import com.cwc.rating.service.entities.Rating;

public interface RatingService {
	
	//add rating
	public Rating createRating(Rating rating);
	//get all rating by user
	public List<Rating> getRatingListByUser(String userId);
	//get all  rating
	public List<Rating> getAllRatingList();
	//get hotel rating list
	public List<Rating> getRatingByHotelId(String hotelId);

	public Rating getSingleRating(String ratingId);
}
