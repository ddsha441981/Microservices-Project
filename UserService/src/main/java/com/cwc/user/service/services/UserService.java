package com.cwc.user.service.services;

import java.util.List;

import com.cwc.user.service.entities.User;

public interface UserService {
	
	//User Operations
	
	//Create User
	User saveUser(User user);
	
	//get all user
	List<User> getAllUser();
	
	//get single user of given userId
	User getUser(String userId);
	
	//TODO : delete
	//TODO : update 

}
