package com.flightbooking.app.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
@RequestMapping("/api/v1.0/flight")
public class UserController {

	@Autowired
	private UserFlightService userFlightService;

	@PostMapping("/search")
	public ResponseEntity<List<Flights>> searchFlights(@RequestBody FlightListInfo flightReq) {
		//User should be able to search for flight based on date/time, from place/to place, one way or round trip 
		//Search result should contain date & time, airline name & logo, price, from & to and round trip (if selected) 
		if(flightReq!=null && 
				flightReq.getFromPlace()!=null && flightReq.getToPlace()!=null) {
			timeConverter(flightReq);
			return ResponseEntity.ok(userFlightService.searchForFlights(flightReq));
		}
		return null;
	}

	@PostMapping("/booking/{flightId}")
	public ResponseEntity<String> bookFlight(@PathVariable("flightId") Integer flightId,@RequestBody UserBooking userBooking) {
		userBooking.setFlightId(flightId);
		String pnrData= userFlightService.bookTickets(userBooking);
		if(pnrData!=null)
			return ResponseEntity.ok(pnrData + " is your generated PNR number");
		return ResponseEntity.badRequest().body("Unable to get PNR for the provided flight details");
	}

	@GetMapping("/ticket/{pnr}")
	public ResponseEntity<UserBooking> fetchTicket(@PathVariable("pnr") String pnr) {
		if(userFlightService.findByPnr(pnr)!=null)
			return ResponseEntity.ok(userFlightService.findByPnr(pnr));
		return new ResponseEntity<>(null,HttpStatus.NOT_ACCEPTABLE);
	}

	@GetMapping("/tickets/{emailid}")
	public ResponseEntity<List<UserBooking>> getTicketsbyEmailId(@PathVariable("emailid") String emailId) {
		if(userFlightService.findByEmailId(emailId)!=null)
			return userFlightService.findByEmailId(emailId);
		return new ResponseEntity<>(null,HttpStatus.NOT_ACCEPTABLE);
	}

	@DeleteMapping("/cancel/{pnr}")
	public String ticketCancellation(@PathVariable("pnr") String pnr) {
		return userFlightService.deleteByPnr(pnr);
	}

	private void timeConverter(FlightListInfo flightReq) {
		if(flightReq.getOnboardDate()!=null) {
		StringBuilder sb=new StringBuilder();
		Date date=null;
		sb.append(flightReq.getOnboardDate());
		if(flightReq.getOnboardTime()==null || flightReq.getOnboardTime().isBlank()) sb.append(" "+"00:00:00");
		else sb.append(" "+flightReq.getOnboardTime());
		
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss",Locale.ENGLISH);
		try {
//			formatter.setTimeZone(TimeZone.getTimeZone("UTC"));
			date = formatter.parse(sb.toString());
			flightReq.setDateTime(date);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		log.info(flightReq.getDateTime()+ " ");
		}
	}
}
