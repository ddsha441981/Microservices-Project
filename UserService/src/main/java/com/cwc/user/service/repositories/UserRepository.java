package com.cwc.user.service.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cwc.user.service.entities.User;

public interface UserRepository extends JpaRepository<User, String> {

	
}
