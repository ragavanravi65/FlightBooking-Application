package com.flightbooking.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.flightbooking.app.model.entity.Passenger;

public interface PassengerRepository extends JpaRepository<Passenger, Integer>{

}
