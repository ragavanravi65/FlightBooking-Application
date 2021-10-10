package com.flightbooking.app.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.flightbooking.app.model.entity.AirlineInfo;
import com.flightbooking.app.model.entity.Flights;
import com.flightbooking.app.repository.AirLineRepository;
import com.flightbooking.app.repository.FlightsListRepository;
import com.flightbooking.app.service.AdminFlightService;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class AdminFlightServiceImpl implements AdminFlightService{

	@Autowired
	private FlightsListRepository flightsListRepo;
	
	@Autowired
	private AirLineRepository airLineRepository;
	public void saveAirlineDetails(Flights flight) {
		flightsListRepo.save(flight);
	}
	
	public AirlineInfo updateAirLineBlocker(AirlineInfo airline) {
		
		return airLineRepository.save(airline);
	}

}
