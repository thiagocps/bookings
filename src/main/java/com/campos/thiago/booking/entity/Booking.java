package com.campos.thiago.booking.entity;


import java.math.BigDecimal;
import java.time.LocalDate;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "bookings")
public class Booking {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;

    @Column(name = "rentValue")
    private BigDecimal rentValue;
    
    @Column(name = "startDate")
    private LocalDate startDate;
    
    @Column(name = "endDate")
    private LocalDate endDate;
    
    @Column(name = "block")
    private boolean block;
    
	@OneToOne(cascade = CascadeType.ALL)
    @JoinTable(name="booking_property",
              joinColumns={@JoinColumn(name="booking_id", referencedColumnName="id")},  
              inverseJoinColumns={@JoinColumn(name="property_id", referencedColumnName="id")})
	private Property property;
    
	@ManyToOne(cascade = CascadeType.ALL)
    @JoinTable(name="booking_guest",
              joinColumns={@JoinColumn(name="booking_id", referencedColumnName="id")},  
              inverseJoinColumns={@JoinColumn(name="guest_id", referencedColumnName="id")})
	private Guest guest;
    
}
