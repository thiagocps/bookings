package com.campos.thiago.booking.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.campos.thiago.booking.entity.Booking;
import com.campos.thiago.booking.exception.BookingNotFoundException;
import com.campos.thiago.booking.exception.DatesInvalidException;
import com.campos.thiago.booking.exception.GuestNotFoundException;
import com.campos.thiago.booking.exception.PeriodNotAvailableException;
import com.campos.thiago.booking.exception.PropertyNotFoundException;
import com.campos.thiago.booking.repository.BookingRepository;
import com.campos.thiago.booking.service.BookingService;
import com.campos.thiago.booking.to.BookingDatesTO;
import com.campos.thiago.booking.to.BookingTO;

import jakarta.validation.Valid;


@CrossOrigin(origins = "https://bookings-prthiagocps.b4a.run:8081")
@RestController
@RequestMapping("/v1")
public class BookingController {

	@Autowired
	BookingService service;
	
	@Autowired
	BookingRepository repository;

	@GetMapping("/bookings")
	public ResponseEntity<List<Booking>> getAllBookings() {
		try {
			List<Booking> bookings = service.findAll();
			
			if (bookings.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}

			return new ResponseEntity<>(bookings, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("/bookings/{id}")
	public ResponseEntity<?> getBookingById(@PathVariable("id") int id) {
		try {
			return new ResponseEntity<>(service.findById(id), HttpStatus.OK);
		}catch(BookingNotFoundException e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
		}
	}

	@PostMapping("/bookings")
	public ResponseEntity<?> createBooking(@Valid @RequestBody BookingTO b) {

		try {
			Booking booking = service.insertBooking(b);
			if(booking.isBlock()) {
				return new ResponseEntity<>("Block ID " + booking.getId() + " created with success. ", HttpStatus.CREATED);
			}else {
				return new ResponseEntity<>("Booking ID " + booking.getId() + " created with success. ", HttpStatus.CREATED);
			}
		} catch (PeriodNotAvailableException | PropertyNotFoundException | GuestNotFoundException | DatesInvalidException e) {
			e.printStackTrace();
			return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_ACCEPTABLE);
		} catch (NullPointerException e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PutMapping("/bookings/{id}")
	public ResponseEntity<?> updateBooking(@PathVariable("id") int id, @RequestBody BookingDatesTO newDates) {
		try {
			service.updateBooking(id, newDates);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			
		} catch (PeriodNotAvailableException | PropertyNotFoundException | GuestNotFoundException | DatesInvalidException e) {
			e.printStackTrace();
			return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_ACCEPTABLE);
		} catch (NullPointerException e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		} catch (BookingNotFoundException e) {
			e.printStackTrace();
			return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
		}
	}

	@DeleteMapping("/bookings/{id}")
	public ResponseEntity<HttpStatus> deleteBooking(@PathVariable("id") int id) {
		try {
			service.deleteById(id);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}


}