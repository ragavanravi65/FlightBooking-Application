package com.flightbooking.app.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.flightbooking.app.model.FlightListInfo;
import com.flightbooking.app.model.entity.Flights;
import com.flightbooking.app.model.entity.UserBooking;
import com.flightbooking.app.service.UserFlightService;

@RestController
@RequestMapping("/api/v1.0/flight")
public class UserController {

	@Autowired
	private UserFlightService userFlightService;

	@PostMapping("/search")
	public List<Flights> searchFlights(@RequestBody FlightListInfo flightReq) {
		//User should be able to search for flight based on date/time, from place/to place, one way or round trip 
		//Search result should contain date & time, airline name & logo, price, from & to and round trip (if selected) 

					/*
					 * 
			mandat:
			fromPlace": "string",
			"toPlace": "string"
			date:
			
			optional:
			"roundTrip": true,
			onboardDateTime": "2021-10-10T13:30:59.246Z", ie., date with time
		 */
		if(flightReq!=null && flightReq.getOnboardDate()!=null && 
				!flightReq.getFromPlace().isBlank() && !flightReq.getToPlace().isBlank()) {
			StringBuilder sb=new StringBuilder();
			Date date=null;
			sb.append(flightReq.getOnboardDate());
			if(flightReq.getOnboardTime()==null || flightReq.getOnboardTime().isBlank()) sb.append(" "+"00:00:00.000000");
			else sb.append(" "+flightReq.getOnboardTime());
			
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSSSSS",Locale.ENGLISH);
			try {
				formatter.setTimeZone(TimeZone.getTimeZone("UTC"));
				date = formatter.parse(sb.toString());
				flightReq.setDateTime(date);
			} catch (ParseException e) {
				e.printStackTrace();
			}
			return userFlightService.searchForFlights(flightReq);
		}
		return null;
	}

	@PostMapping("/booking/{flightId}")
	public Long bookFlight(@PathVariable("flightId") Integer flightId,@RequestBody UserBooking userBooking) {
		userBooking.setFlightId(flightId);
		return userFlightService.bookTickets(userBooking);
	}

	@GetMapping("/ticket/{pnr}")
	public ResponseEntity<UserBooking> fetchTicket(@PathVariable("pnr") Long pnr) {
		return ResponseEntity.ok(userFlightService.findByPnr(pnr));
	}

	@GetMapping("/tickets/{emailid}")
	public ResponseEntity<List<UserBooking>> getTicketsbyEmailId(@PathVariable("emailid") String emailId) {
		return userFlightService.findByEmailId(emailId);
	}

	@DeleteMapping("/cancel/{pnr}")
	public String ticketCancellation(@PathVariable("pnr") Long pnr) {
		//need to add logic for departure time calc yet
		return userFlightService.deleteByPnr(pnr);
	}
}
