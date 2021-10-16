package com.flightbooking.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.flightbooking.app.Util.JwtUtil;
import com.flightbooking.app.model.AuthenticationRequest;
import com.flightbooking.app.model.AuthenticationResponse;
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
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private UserDetailsService userDetailsService;
	
	@Autowired
	private JwtUtil jwtUtil;
	
	@PostMapping("/airlines")
	public String createAirlines(@RequestBody Flights flights) {
			return adminFlightService.saveAirlineDetails(flights);
	}
	
//	@PostMapping("/login")
//	public String loginValidator() {
//		
//		return null;
//	}
	
	@PostMapping("/UpdateBlocker")
	public String updateBlocker(@RequestBody AirlineInfo airlineInfo) {
		AirlineInfo returnedValue=adminFlightService.updateAirLineBlocker(airlineInfo);
		return returnedValue!=null?"success":"Issue found";
	}
	
	@PostMapping("/login")
	public ResponseEntity<AuthenticationResponse> validateUserToken(@RequestBody AuthenticationRequest authenticationRequest) throws Exception{
		try {
		authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authenticationRequest.getUsername(), 
				authenticationRequest.getPassword()));
		}
		catch(Exception e) {
			return new ResponseEntity(new AuthenticationResponse("Bad Credentials::Unable to generate token"),HttpStatus.BAD_REQUEST);
		}
		
		UserDetails value = userDetailsService.loadUserByUsername(authenticationRequest.getUsername());
		String token = jwtUtil.generateToken(value);
		return ResponseEntity.ok(new AuthenticationResponse(token));
	} 
}
