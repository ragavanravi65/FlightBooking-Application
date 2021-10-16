package com.flightbooking.app.model;


import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnore;

import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;


@Data
@Builder
public class FlightListInfo {
//	Search result should contain date & time, airline name & logo, price, from & to and round trip (if selected) 
	//mandatory
	private String fromPlace;
	private String toPlace;
	@ApiModelProperty(value = "A JSON value representing date format", example = "yyyy-MM-dd")
	private String onboardDate;
	//optional
	@ApiModelProperty(example="HH:mm:ss")
	private String onboardTime;
	private Boolean roundTrip;
	
	//extra
	private String airLine;
	
	@JsonIgnore
	private Long ticketPrice;
	
	@JsonIgnore
	private Date dateTime;
}
