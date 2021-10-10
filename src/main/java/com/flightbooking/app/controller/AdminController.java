package com.flightbooking.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.flightbooking.app.model.entity.AirlineInfo;
import com.flightbooking.app.model.entity.Flights;
import com.flightbooking.app.service.AdminFlightService;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/api/v1.0/admin")
@Slf4j
public class AdminController {
	@Autowired
	private AdminFlightService adminFlightService;
//	@Autowired
//	AdminController(AdminFlightService adminFlightService){
//		this.adminFlightService=adminFlightService;
//	}
	
	@PostMapping("/airlines")
	public String createAirlines(@RequestBody Flights flights) {
		log.info(flights.toString());
		//check if already exists ,if not create new field in db
		adminFlightService.saveAirlineDetails(flights);
		
		return null;
	}
	
	@PostMapping("/login")
	public String loginValidator() {
		
		return null;
	}
	
	@PostMapping("/UpdateBlocker")
	public String updateBlocker(@RequestBody AirlineInfo airlineInfo) {
		AirlineInfo returnedValue=adminFlightService.updateAirLineBlocker(airlineInfo);
		return returnedValue!=null?"success":"Issue found";
	}
}
