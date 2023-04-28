package com.campos.thiago.booking.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.campos.thiago.booking.entity.Host;

public interface HostRepository extends CrudRepository<Host, Integer> {
	
	Optional<Host> findByRegister(String register);
}