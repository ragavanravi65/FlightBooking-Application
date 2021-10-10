package com.flightbooking.app.service;

import com.flightbooking.app.model.entity.AirlineInfo;
import com.flightbooking.app.model.entity.Flights;

public interface AdminFlightService {

	void saveAirlineDetails(Flights flights);
	
	AirlineInfo updateAirLineBlocker(AirlineInfo airlineInfo);

}
