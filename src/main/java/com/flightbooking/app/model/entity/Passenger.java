package com.flightbooking.app.model.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.flightbooking.app.model.enumPacker.Gender;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;


@Data
@Builder
@Entity
@AllArgsConstructor
public class Passenger {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer pid;
		
	private String pname;
	private Gender gender;
	private int age;
	
	@ManyToOne
	@JsonIgnore
	private UserBooking userbooking;
	
	Passenger(){}
}
