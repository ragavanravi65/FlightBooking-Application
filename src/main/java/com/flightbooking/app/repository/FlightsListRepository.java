package com.flightbooking.app.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.flightbooking.app.model.FlightId;
import com.flightbooking.app.model.FlightListInfo;
import com.flightbooking.app.model.entity.Flights;

@Repository
public interface FlightsListRepository extends JpaRepository<Flights, FlightId>{

	@Query("select f from Flights f where "
			+ "f.fromPlace=:#{#fc.fromPlace} and "
			+ "f.toPlace=:#{#fc.toPlace} "
			+ "and f.flightid.onboardDateTime>=:#{#fc.dateTime} and (:#{#fc.roundTrip} IS NULL OR f.roundTrip=:#{#fc.roundTrip})")
	List<Flights> findFlightsByCriteria(@Param("fc")FlightListInfo fromPlace);

}
