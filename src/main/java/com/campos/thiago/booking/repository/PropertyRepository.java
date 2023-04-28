package com.campos.thiago.booking.repository;

import org.springframework.data.repository.CrudRepository;

import com.campos.thiago.booking.entity.Property;

public interface PropertyRepository extends CrudRepository<Property, Integer> {
}