package com.flightbooking.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.flightbooking.app.model.entity.AirlineInfo;


@Repository
public interface AirLineRepository extends JpaRepository<AirlineInfo, String>{

}
