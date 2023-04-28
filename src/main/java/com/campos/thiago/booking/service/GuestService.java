package com.campos.thiago.booking.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.campos.thiago.booking.entity.Guest;
import com.campos.thiago.booking.exception.GuestNotFoundException;
import com.campos.thiago.booking.repository.GuestRepository;

@Service
public class GuestService {

	@Autowired
	private GuestRepository guestRepository;
	
	public Guest findByRegister(String guestRegister) throws GuestNotFoundException{
		return guestRepository
				.findByRegister(guestRegister)
				.orElseThrow(() -> new GuestNotFoundException());
		
	}
}
