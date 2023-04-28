package com.campos.thiago.booking.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.campos.thiago.booking.entity.Property;
import com.campos.thiago.booking.exception.PropertyNotFoundException;
import com.campos.thiago.booking.repository.PropertyRepository;

@Service
public class PropertyService {

	@Autowired
	private PropertyRepository propertyRepository;
	
	public Property findById(int propertyId) throws PropertyNotFoundException {
		return propertyRepository
				.findById(propertyId)
				.orElseThrow(() -> new PropertyNotFoundException()); 
	}
}
