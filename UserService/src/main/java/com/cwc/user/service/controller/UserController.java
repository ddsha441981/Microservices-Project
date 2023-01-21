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

@RestController
@RequestMapping("/users")
public class UserController {

	@Autowired
	private UserService userService;
	//create user
	@PostMapping("/")
	public ResponseEntity<User> createUser(@RequestBody User user){
		User savedUser = userService.saveUser(user);
		return ResponseEntity.status(HttpStatus.CREATED).body(savedUser);
	}
	//get single user
	@GetMapping("/{userId}")
	public ResponseEntity<User> getSingleUser(@PathVariable String userId){
		User user = userService.getUser(userId);
		return ResponseEntity.ok(user);
	}
	
	//get all user
	@GetMapping("/")
	public ResponseEntity<List<User>> getAllUsers(){
		List<User> allUser = userService.getAllUser();
		return ResponseEntity.ok(allUser);
		
	}
}
