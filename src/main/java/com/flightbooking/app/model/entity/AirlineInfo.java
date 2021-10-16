package com.flightbooking.app.model.entity;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;


@Entity
@Data
@Table(name = "airline_info")
public class AirlineInfo {

	@Id
	private String airline;
	
	@OneToMany(mappedBy="airlineInfos")
	@JsonIgnore
	private List<Flights> flights;
	private String blockStatus;
	@Override
	public String toString() {
		return "AirlineInfo [airline=" + airline + ", blockStatus=" + blockStatus + "]";
	}
}
