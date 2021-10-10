package com.flightbooking.app.model.entity;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import lombok.Data;


@Entity
@Data
public class AirlineInfo {

	@Id
	private String airline;
	
	@OneToMany(mappedBy="airlineInfos")
	private List<Flights> flights;
	private String blockStatus;
	@Override
	public String toString() {
		return "AirlineInfo [airline=" + airline + ", flights=" + flights
				+ ", blockStatus=" + blockStatus + "]";
	}

	

}
