package com.flightbooking.app.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.flightbooking.app.model.entity.AirlineInfo;


@Repository
public interface AirLineRepository extends JpaRepository<AirlineInfo, String>{

	@Query(value="select DISTINCT(airline) from testschema.airline_info",nativeQuery=true)
	List<String> getAllairLineData();

	@Modifying
    @Transactional
	@Query(value="UPDATE testschema.airline_info ar SET ar.block_status=:bs where ar. airline=:ar",nativeQuery=true)
	void updateBlockStatusByAR(@Param("bs")String blockStatus,@Param("ar")String ar);
}
