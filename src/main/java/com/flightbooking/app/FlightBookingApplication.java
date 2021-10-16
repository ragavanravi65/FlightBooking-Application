package com.flightbooking.app;

import java.util.TimeZone;

import javax.annotation.PostConstruct;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class FlightBookingApplication {

	public static void main(String[] args) {
		SpringApplication.run(FlightBookingApplication.class, args);
	}
	
	@PostConstruct
	void setTimeZoneFormat() {
	    TimeZone.setDefault(TimeZone.getTimeZone("UTC"));
	}

}
