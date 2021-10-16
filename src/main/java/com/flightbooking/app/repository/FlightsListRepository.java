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
			+ "(:#{#fc.fromPlace} IS NULL OR f.fromPlace=:#{#fc.fromPlace}) and "
			+ "(:#{#fc.toPlace} IS NULL OR f.toPlace=:#{#fc.toPlace}) and "
			+ "(:#{#fc.dateTime} IS NULL OR f.flightid.onboardDateTime>=:#{#fc.dateTime}) and "
			+ "(:#{#fc.roundTrip} IS NULL OR f.roundTrip=:#{#fc.roundTrip})")
	List<Flights> findFlightsByCriteria(@Param("fc")FlightListInfo fromPlace);
	
	
	@Query(value="select * from flights f where f.flightno=:flightId and f.from_place=:fp and f.to_place=:tp",nativeQuery=true)
	Flights getFlightToBook(@Param("flightId")long flightId,@Param("fp") String fromPlace,@Param("tp") String toPlace);

}
