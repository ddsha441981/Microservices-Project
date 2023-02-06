package com.cwc.hotel.service.entities;



import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Entity
@Table(name = "hotels")
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
//@Node
public class Hotel {

	@Id
	private String hotelId;
	private String name;
	private String location;
	private String about;
}
