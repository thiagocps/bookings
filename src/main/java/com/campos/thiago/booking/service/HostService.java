package com.campos.thiago.booking.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.campos.thiago.booking.entity.Host;
import com.campos.thiago.booking.exception.HostNotFoundException;
import com.campos.thiago.booking.repository.HostRepository;

@Service
public class HostService {

	@Autowired
	private HostRepository hostRepository;
	
	public Host findByRegister(String hostRegister) throws HostNotFoundException{
		return hostRepository
				.findByRegister(hostRegister)
				.orElseThrow(() -> new HostNotFoundException());
		
	}
}
