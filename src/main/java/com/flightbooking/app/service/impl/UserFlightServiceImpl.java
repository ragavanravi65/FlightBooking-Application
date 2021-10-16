package com.flightbooking.app.service.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collector;
import java.util.stream.Collectors;

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
		List<Flights> filteredFlightResponseLists=flightResponseLists.stream()
				.filter(eachFlight->eachFlight!=null && eachFlight.getAirlineInfos()!=null &&
				"NB".equalsIgnoreCase(eachFlight.getAirlineInfos().getBlockStatus()))
				.collect(Collectors.toList());
		return filteredFlightResponseLists;

	}
	@Override
	public String bookTickets(UserBooking userBooking) {
		Flights flightDetail = flightsListRepo.getFlightToBook(userBooking.getFlightId(),userBooking.getFromPlace(),userBooking.getToPlace());
		log.info(flightDetail+" is the desired data");
		if(flightDetail!=null) {
			UserBooking returnedEntry = userBookingRepository.save(userBooking);
			return returnedEntry.getPnr();
		}
		return null;
	}
	public UserBooking findByPnr(String pnr) {
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
	public String deleteByPnr(String pnr) {
		Optional<UserBooking> returnedDetail = userBookingRepository.findById(pnr);
		UserBooking userDetail = null;
		if(returnedDetail.isPresent())
				userDetail=returnedDetail.get();
		try {
			long departureDiff=findDateDiff(userDetail.getOnboardDateTime());
			if(departureDiff>24) {
				userBookingRepository.deleteById(pnr);
				return "Deletion was successful";
			}
			else
				return "Departure time is less than 24 hours";
		} catch (ParseException e) {
			log.info("Issue with finding difference in departure time : {} ",e.getMessage());
		}
		
		return "Issue with deletion has been noticed";
		
	}
	
	public static long findDateDiff(Date first) throws ParseException{
		SimpleDateFormat utcdate=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String obTimeString=utcdate.format(first);
		log.info(obTimeString);
		Date date = new Date();  
		String currentDate = (utcdate.format(date)); 
		log.info(currentDate);
		Date now=utcdate.parse(currentDate);

		Date receivedDate=utcdate.parse(obTimeString);

		Calendar cal=Calendar.getInstance();
		cal.setTime(receivedDate);
//		cal.add(Calendar.HOUR_OF_DAY, +0);
		
		return (now.getTime()-receivedDate.getTime())/(1000*60*60);

	}
}
