package com.flightbooking.app.service;

import com.flightbooking.app.model.entity.AirlineInfo;
import com.flightbooking.app.model.entity.Flights;

public interface AdminFlightService {

	String saveAirlineDetails(Flights flights);
	
	AirlineInfo updateAirLineBlocker(AirlineInfo airlineInfo);

}
