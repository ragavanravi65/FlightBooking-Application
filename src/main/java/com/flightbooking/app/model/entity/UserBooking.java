package com.flightbooking.app.model.entity;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

import lombok.Data;


@Data
@Entity
public class UserBooking {
	@Id	
	@GeneratedValue(strategy=GenerationType.IDENTITY) //to be enhanced further
	@Column(name="pnr")
	private Long pnr;
	
	private long flightId;
	private Date onboardDateTime;
	private String fromPlace;
	private String toPlace;
	private String name;
	private String emailId;
	private int seatCount;
	
	@OneToMany(targetEntity = Passenger.class, cascade = CascadeType.ALL)
	@JoinColumn(name="pnr")
	private List<Passenger> passenger;
	
	
	
}
