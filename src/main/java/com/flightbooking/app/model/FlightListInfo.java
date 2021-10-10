package com.flightbooking.app.model;


import java.util.Date;

import lombok.Builder;
import lombok.Data;


@Data
@Builder
public class FlightListInfo {
//	Search result should contain date & time, airline name & logo, price, from & to and round trip (if selected) 
	//mandatory
	private String fromPlace;
	private String toPlace;
	private String onboardDate;
	//optional
	private String onboardTime;
	private Boolean roundTrip;
	
	//extra
	private String airLine;
	private Long ticketPrice;
	
	private Date dateTime;
}
