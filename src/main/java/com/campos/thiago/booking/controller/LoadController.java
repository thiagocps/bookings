package com.campos.thiago.booking.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.campos.thiago.booking.entity.Booking;
import com.campos.thiago.booking.entity.Guest;
import com.campos.thiago.booking.entity.Host;
import com.campos.thiago.booking.entity.Property;
import com.campos.thiago.booking.entity.TypeProperty;
import com.campos.thiago.booking.exception.BookingNotFoundException;
import com.campos.thiago.booking.exception.GuestNotFoundException;
import com.campos.thiago.booking.exception.PeriodNotAvailableException;
import com.campos.thiago.booking.exception.PropertyNotFoundException;
import com.campos.thiago.booking.repository.BookingRepository;
import com.campos.thiago.booking.repository.GuestRepository;
import com.campos.thiago.booking.repository.PropertyRepository;
import com.campos.thiago.booking.service.BookingService;
import com.campos.thiago.booking.to.BookingTO;


@CrossOrigin(origins = "http://localhost:8081")
@RestController
@RequestMapping("/v1")
public class LoadController {

	@Autowired
	BookingService service;
	
	@Autowired
	BookingRepository repository;
	
	@Autowired
	GuestRepository guestRepository;
	
	@Autowired
	PropertyRepository propertyRepository;

	@PostMapping("/load")
	public ResponseEntity<?> createBackground() {

		Guest g1 = new Guest();
		g1.setName("Thiago Campos");
		g1.setRegister("123");
		
		Guest g2 = new Guest();
		g2.setName("Ana Paula Campos");
		g2.setRegister("321");
		
		Guest g3 = new Guest();
		g3.setName("Maria Alice");
		g3.setRegister("456");
		
		guestRepository.save(g1);
		guestRepository.save(g2);
		guestRepository.save(g3);
		
		Host h1 = new Host();
		h1.setName("Maria Alice");
		h1.setRegister("456");
		
		Property p1 = new Property();
		p1.setAddress("Avenida 1");
		p1.setCity("Campinas");
		p1.setType(TypeProperty.URBAN_HOUSE);
		p1.setHost(h1);
		
		Host h2 = new Host();
		h2.setName("Maria Alice");
		h2.setRegister("789");
		
		Property p2 = new Property();
		p2.setAddress("Avenida 2");
		p2.setCity("Guarujá");
		p2.setType(TypeProperty.BEACH_HOUSE);
		p2.setHost(h2);
		
		Host h3 = new Host();
		h3.setName("Maria Alice");
		h3.setRegister("101");
		
		Property p3 = new Property();
		p3.setAddress("Avenida 3");
		p3.setCity("Jacutinga");
		p3.setType(TypeProperty.COUNTRY_HOUSE);
		p3.setHost(h3);
		
		propertyRepository.save(p1);
		propertyRepository.save(p2);
		propertyRepository.save(p3);
		
		return new ResponseEntity<>(HttpStatus.CREATED);
	}

}