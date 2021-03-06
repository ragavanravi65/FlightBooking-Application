package com.flightbooking.app.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.flightbooking.app.model.FlightListInfo;
import com.flightbooking.app.model.entity.Flights;
import com.flightbooking.app.model.entity.UserBooking;

public interface UserFlightService {

	public List<Flights> searchForFlights(FlightListInfo flightReq);

	public Long bookTickets(UserBooking userBooking);

	public UserBooking findByPnr(Long pnr);

	public ResponseEntity<List<UserBooking>> findByEmailId(String emailId);

	public String deleteByPnr(Long pnr);
}
