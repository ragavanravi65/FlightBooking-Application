package com.flightbooking.app.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.flightbooking.app.model.FlightListInfo;
import com.flightbooking.app.model.entity.Flights;
import com.flightbooking.app.model.entity.UserBooking;
import com.flightbooking.app.repository.FlightsListRepository;
import com.flightbooking.app.repository.UserBookingRepository;
import com.flightbooking.app.service.UserFlightService;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class UserFlightServiceImpl implements UserFlightService{

	@Autowired
	private FlightsListRepository flightsListRepo;
	
	@Autowired
	private UserBookingRepository userBookingRepository;
	public List<Flights> searchForFlights(FlightListInfo flightReq) {
		log.info(flightReq.toString());
		List<Flights> flightResponseLists = flightsListRepo.findFlightsByCriteria(flightReq);
		return flightResponseLists;
		
	}
	@Override
	public Long bookTickets(UserBooking userBooking) {
		UserBooking returnedEntry = userBookingRepository.save(userBooking);
		return returnedEntry.getPnr();
	}
	public UserBooking findByPnr(Long pnr) {
		Optional<UserBooking> returnedDetail = userBookingRepository.findById(pnr);
		if(returnedDetail.isPresent())
				return returnedDetail.get();
		return null;
	}
	@Override
	public ResponseEntity<List<UserBooking>> findByEmailId(String emailId) {
		List<UserBooking> obtainedResults=userBookingRepository.findByEmailId(emailId);
		return ResponseEntity.ok(obtainedResults);
	}
	@Override
	public String deleteByPnr(Long pnr) {
		userBookingRepository.deleteById(pnr);
		return "Deletion was successful";
	}
}
