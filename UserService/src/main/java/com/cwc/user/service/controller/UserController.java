package com.cwc.user.service.controller;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cwc.user.service.config.MapperConfig;
import com.cwc.user.service.entities.User;
import com.cwc.user.service.services.UserService;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import io.github.resilience4j.retry.annotation.Retry;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/users")
//@Slf4j
public class UserController {

	Logger logger = LogManager.getLogger(UserController.class);
	
	@Autowired
	private UserService userService;

	// create user
	@PostMapping("/")
	public ResponseEntity<User> createUser(@RequestBody User user) {
		logger.info("UserController : createUser execution started..");
		logger.info("UserController : createUser request payload {} ", MapperConfig.mapToJsonString(user));
		
		User savedUser = userService.saveUser(user);
		
		logger.info("UserController : create User response payload {} ", MapperConfig.mapToJsonString(savedUser));
		logger.info("UserController : createUser execution ended..");
		return ResponseEntity.status(HttpStatus.CREATED).body(savedUser);
	}

	// get single user
	/** 
	 * implemented circuit-breaker to prevent services fails,fallback and provide particular message 
		There are many types of circuit-breaker
	 * 1. Hystrix (Used in old versions)
	 * 2. Resilience4j
	 * 3. Sentinel
	 * 4. Spring Retry
	 * 5. Rate Limiter
	 * **/
	int retryCount = 1;
	@GetMapping("/{userId}")
//	@CircuitBreaker(name = "ratingHotelBreaker", fallbackMethod = "ratingHotelBreaker")
//	@Retry(name="ratingHotelService",fallbackMethod = "ratingHotelBreaker")
	@RateLimiter(name = "userRateLimter",fallbackMethod = "ratingHotelBreaker")
	public ResponseEntity<User> getSingleUser(@PathVariable String userId) {
		User user = userService.getUser(userId);
		logger.info("Get Single User User Controller  : {} ");
		logger.info("Retry count : {} ",retryCount);
		retryCount++;
		return ResponseEntity.ok(user);
	}

	// CircuitBreaker Resilience4j fallback method
	public ResponseEntity<User> ratingHotelBreaker(String userId, Exception ex) {
		logger.info("Fallback is executed : Some Service is Down", ex.getMessage());
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
		logger.info("UserController : getAllUsers execution started..");
		
		List<User> allUser = userService.getAllUser();
		
		logger.info("UserController : createUser response payload {} ", MapperConfig.mapToJsonString(allUser));
		logger.info("UserController : getAllUsers execution ended..");
		return ResponseEntity.ok(allUser);

	}
}
