package com.cwc.user.service.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cwc.user.service.entities.User;
@Repository
public interface UserRepository extends JpaRepository<User, String> {

	
}
