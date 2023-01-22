package com.cwc.user.service.services.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.cwc.user.service.entities.Hotel;
import com.cwc.user.service.entities.Rating;
import com.cwc.user.service.entities.User;
import com.cwc.user.service.exception.ResourceNotFoundException;
import com.cwc.user.service.repositories.UserRepository;
import com.cwc.user.service.services.UserService;

import lombok.extern.apachecommons.CommonsLog;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@CommonsLog
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private RestTemplate restTemplate;

	@Override
	public User saveUser(User user) {
		// generate unique id
		String randomUserId = UUID.randomUUID().toString();
		user.setUserId(randomUserId);
		return userRepository.save(user);
	}

	@Override
	public List<User> getAllUser() {
		return userRepository.findAll();
	}

	@Override
	public User getUser(String userId) {
		// get user from database with help of userId
		User user = userRepository.findById(userId).orElseThrow(
				() -> new ResourceNotFoundException("User with given id is not found on server!!! " + userId));
		// now we get rating from Rating Service with help of userId
		// URL: http://localhost:8083/ratings/users/5fd98782-59d9-489d-b869-840f96b9b408

		Rating[] ratingOfUser = restTemplate.getForObject("http://localhost:8083/ratings/users/" + user.getUserId(),Rating[].class);
		log.info("{}", ratingOfUser);

		List<Rating> ratings = Arrays.stream(ratingOfUser).toList();

		List<Rating> ratingList = ratings.stream().map(rating -> {
			// API call to hotel service to get the hotel
			// URL: http://localhost:8082/hotels/40abc01a-addf-4c14-a438-c52964ec3231
			ResponseEntity<Hotel> forEntity = restTemplate
					.getForEntity("http://localhost:8082/hotels/" + rating.getHotelId(), Hotel.class);
			Hotel hotel = forEntity.getBody();
			// set the hotel to rating
			rating.setHotel(hotel);
			log.info("response status code ", forEntity.getStatusCode());
			// return the rating
			return rating;
		}).collect(Collectors.toList());

		user.setRatings(ratingList);

		return user;
	}

}
