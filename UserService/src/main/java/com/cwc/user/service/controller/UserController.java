package com.cwc.user.service.controller;

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

import com.cwc.user.service.entities.User;
import com.cwc.user.service.services.UserService;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/users")
@Slf4j
public class UserController {

	@Autowired
	private UserService userService;

	// create user
	@PostMapping("/")
	public ResponseEntity<User> createUser(@RequestBody User user) {
		User savedUser = userService.saveUser(user);
		log.info("Create New User Handler : UserController");
		return ResponseEntity.status(HttpStatus.CREATED).body(savedUser);
	}

	// get single user
	@GetMapping("/{userId}")
	@CircuitBreaker(name = "ratingHotelBreaker", fallbackMethod = "ratingHotelBreaker")
	public ResponseEntity<User> getSingleUser(@PathVariable String userId) {
		User user = userService.getUser(userId);
		log.info("Get Single User Handler : UserController");
		return ResponseEntity.ok(user);
	}

	// CircuitBreaker Resilience4j fallback method
	public ResponseEntity<User> ratingHotelBreaker(String userId, Exception ex) {
		log.info("Fallback is executed : Some Service is Down", ex.getMessage());
		User user = User.builder()
				.name("dammy User")
				.email("dammy@gmail.com")
				.about("Dammy User is created beacause some service is down")
				.userId("123456789")
				.build();
		return new ResponseEntity<User>(user, HttpStatus.CREATED);
	}

	// get all user
	@GetMapping("/")
	public ResponseEntity<List<User>> getAllUsers() {
		List<User> allUser = userService.getAllUser();
		log.info("Get All User Handler : UserController");
		return ResponseEntity.ok(allUser);

	}
}
