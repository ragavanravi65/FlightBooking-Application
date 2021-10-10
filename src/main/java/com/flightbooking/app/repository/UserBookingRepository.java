package com.flightbooking.app.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.flightbooking.app.model.entity.UserBooking;

public interface UserBookingRepository extends JpaRepository<UserBooking, Long>{

	List<UserBooking> findByEmailId(String emailId);

}
