package com.flightbooking.app.model.entity;

import javax.persistence.CascadeType;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.flightbooking.app.model.FlightId;
import com.flightbooking.app.model.enumPacker.Meal;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Entity
@Data
@Builder
@AllArgsConstructor
public class Flights {

	@EmbeddedId
	private FlightId flightid;
	private String fromPlace;
	private String toPlace;
	private Boolean roundTrip;
	private Long ticketPrice;
	private String instrument;


	private Meal mealChoice;

	@ManyToOne(cascade = CascadeType.MERGE)
	@JoinColumn(name="airline")
	@JsonIgnore
	private AirlineInfo airlineInfos;
	
	Flights(){}
}
