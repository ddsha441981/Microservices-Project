package com.cwc.rating.service.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cwc.rating.service.entities.Rating;
import com.cwc.rating.service.exception.ResourceNotFoundException;
import com.cwc.rating.service.repositories.RatingRepository;
import com.cwc.rating.service.services.RatingService;

@Service
public class RatingServiceImpl implements RatingService {

	@Autowired
	private RatingRepository ratingRepository;
	@Override
	public Rating createRating(Rating rating) {
		Rating savedRating = ratingRepository.save(rating);
		return savedRating;
	}

	@Override
	public List<Rating> getRatingListByUser(String userId) {
		return ratingRepository.findByUserId(userId);
	}

	@Override
	public List<Rating> getAllRatingList() {
		List<Rating> ratingList = ratingRepository.findAll();
		return ratingList;
	}

	@Override
	public List<Rating> getRatingByHotelId(String hotelId) {
		return ratingRepository.findByHotelId(hotelId);
	}

	@Override
	public Rating getSingleRating(String ratingId) {
		Rating rating = ratingRepository.findById(ratingId).orElseThrow(()->new ResourceNotFoundException("rating not found with givin ratingId"));
		return rating;
	}

}
