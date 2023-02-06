package com.cwc.user.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.cwc.user.service.entities.Rating;
import com.cwc.user.service.external.apiservices.RatingService;

import lombok.extern.slf4j.Slf4j;

@SpringBootTest
@Slf4j
class UserServiceApplicationTests {

//	@Test
//	void contextLoads() {
//	}
	
	@Autowired
	private RatingService ratingService;
	
	@Test
	void createRating() {
		//builder pattern to set data manually
		Rating rating = Rating.builder().rating(7).hotelId("").userId("").feedback("This is feign client test data").build();
		ResponseEntity<Rating> savedRating = ratingService.createRating(rating);
		HttpHeaders headers = savedRating.getHeaders();
		HttpStatus statusCode = savedRating.getStatusCode();
		log.info("{}",savedRating,headers,statusCode);
		System.out.println("Rating Created");
	}

}
