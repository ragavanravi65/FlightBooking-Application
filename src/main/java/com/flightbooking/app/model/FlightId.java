package com.flightbooking.app.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Embeddable;

import lombok.Builder;
import lombok.Data;

@Embeddable
@Data
@Builder
public class FlightId implements Serializable{
	
	private Long flightno;
	private Date onboardDateTime;
	private Date terminalDateTime;
	
	public FlightId(){
		
	}
	public FlightId(Long flightno,Date onboardDateTime,Date terminalDatetTime) {
		this.flightno=flightno;
		this.onboardDateTime=onboardDateTime;
		this.terminalDateTime=terminalDatetTime;
	}

}
