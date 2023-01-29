package com.cwc.hotel.service.repositories;

import org.springframework.data.neo4j.repository.Neo4jRepository;

import com.cwc.hotel.service.entities.Hotel;

public interface HotelRepository extends Neo4jRepository<Hotel, String>{

}
