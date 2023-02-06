package com.cwc.user.service.services.impl;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.cwc.user.service.entities.Hotel;
import com.cwc.user.service.entities.Rating;
import com.cwc.user.service.entities.User;
import com.cwc.user.service.exception.ResourceNotFoundException;
import com.cwc.user.service.external.apiservices.HotelService;
import com.cwc.user.service.repositories.UserRepository;
import com.cwc.user.service.services.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
    private UserRepository userRepository;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private HotelService hotelService;
//    private HotelService hotelService;

    private Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    @Override
    public User saveUser(User user) {
        //generate  unique userid
        String randomUserId = UUID.randomUUID().toString();
        user.setUserId(randomUserId);
        return userRepository.save(user);
    }

    @Override
    public List<User> getAllUser() {
        //implement RATING SERVICE CALL: USING REST TEMPLATE
        return userRepository.findAll();
    }

    //get single user using feign client
    @Override
    public User getUser(String userId) {
        //get user from database with the help  of user repository
        User user = userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User with given id is not found on server !! : " + userId));
        // fetch rating of the above  user from RATING SERVICE
        //http://localhost:8083/ratings/users/47e38dac-c7d0-4c40-8582-11d15f185fad

        Rating[] ratingsOfUser = restTemplate.getForObject("http://RATING-SERVICE/ratings/users/" + user.getUserId(), Rating[].class);
//        logger.info("{} ", ratingsOfUser);
        List<Rating> ratings = Arrays.stream(ratingsOfUser).toList();
        List<Rating> ratingList = ratings.stream().map(rating -> {
            //api call to hotel service to get the hotel
            //http://localhost:8082/hotels/1cbaf36d-0b28-4173-b5ea-f1cb0bc0a791
            ResponseEntity<Hotel> forEntity = restTemplate.getForEntity("http://HOTEL-SERVICE/hotels/"+rating.getHotelId(), Hotel.class);
            //Hotel hotel = hotelService.getHotel(rating.getHotelId());//Here throw exception <-----------------------
            Hotel hotel = forEntity.getBody();
           // logger.info("response status code: {} ",forEntity.getStatusCode());
            //set the hotel to rating
            rating.setHotel(hotel);
            //return the rating
        	  logger.info("{} -----------------------------", rating);
            return rating;
        }).collect(Collectors.toList());

        user.setRatings(ratingList);

        return user;
    }
    
 // Micro services Calling by using RestTemplate
  //@Override
  //public User getUser(String userId) {
  //// get user from database with help of userId
  //User user = userRepository.findById(userId).orElseThrow(
  //() -> new ResourceNotFoundException("User with given id is not found on server!!! " + userId));
  //// now we get rating from Rating Service with help of userId
  //// URL: http://localhost:8083/ratings/users/5fd98782-59d9-489d-b869-840f96b9b408
  //
  //Rating[] ratingOfUser = restTemplate.getForObject("http://RATING-SERVICE/ratings/users/" + user.getUserId(),Rating[].class);
  //log.info("{}", ratingOfUser);
  //
  //List<Rating> ratings = Arrays.stream(ratingOfUser).toList();
  //
  //List<Rating> ratingList = ratings.stream().map(rating -> {
  //// API call to hotel service to get the hotel
  //// URL: http://localhost:8082/hotels/40abc01a-addf-4c14-a438-c52964ec3231
  //ResponseEntity<Hotel> forEntity = restTemplate
  //.getForEntity("http://HOTEL-SERVICE/hotels/" + rating.getHotelId(), Hotel.class);
  //Hotel hotel = forEntity.getBody();
  //// set the hotel to rating
  //rating.setHotel(hotel);
  //log.info("response status code ", forEntity.getStatusCode());
  //// return the rating
  //return rating;
  //}).collect(Collectors.toList());
  //
  //user.setRatings(ratingList);
  //
  //return user;
  //}

}