package com.campos.thiago.booking.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.campos.thiago.booking.entity.Guest;

public interface GuestRepository extends CrudRepository<Guest, Integer> {
	
	Optional<Guest> findByRegister(String register);
}