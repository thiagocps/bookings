package com.campos.thiago.booking.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.campos.thiago.booking.entity.Booking;

public interface BookingRepository extends CrudRepository<Booking, Integer> {
	
	@Query(value = "SELECT b FROM Booking b INNER JOIN b.property p WHERE p.id = :propertyId AND ((b.startDate BETWEEN :startDate AND :endDate) OR (b.endDate BETWEEN :startDate AND :endDate))")
	List<Booking> findBookingsOfPropertyInPeriod(@Param("propertyId") int propertyId, @Param("startDate") LocalDate startDate,  @Param("endDate") LocalDate endDate);
}