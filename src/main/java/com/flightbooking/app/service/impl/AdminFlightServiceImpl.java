package com.flightbooking.app.service.impl;

import java.util.List;
import java.util.Optional;

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
	public String saveAirlineDetails(Flights flight) {
		String airLine=flight.getAirlineInfos()!=null?flight.getAirlineInfos().getAirline():null;
		if(airLine!=null) {
			List<String> airLineList = airLineRepository.getAllairLineData();
			if(airLineList.contains(airLine)) {
				Optional<AirlineInfo> airLineInfo = airLineRepository.findById(airLine);
				String arI=airLineInfo.isPresent()?airLineInfo.get().getBlockStatus():null;
				flightsListRepo.save(flight);
				airLineRepository.updateBlockStatusByAR(arI,airLine);
				return "Flight details has been updated";
			}
		}
		return "Provide Valid airline with flight Details";
	}
	public AirlineInfo updateAirLineBlocker(AirlineInfo airline) {

		return airLineRepository.save(airline);
	}

}
