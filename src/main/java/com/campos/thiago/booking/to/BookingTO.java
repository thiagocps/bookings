package com.campos.thiago.booking.to;

import java.math.BigDecimal;
import java.time.LocalDate;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class BookingTO {
	
	@NotNull(message = "startDate is mandatory")
	private LocalDate startDate;
	
	@NotNull(message = "endDate is mandatory")
	private LocalDate endDate;
	
	@NotNull(message = "rentValue is mandatory")
	private BigDecimal rentValue;
	
	@NotNull(message = "propertyId is mandatory")
	private int propertyId;
	
	@NotNull(message = "guestId is mandatory")
	private String guestId;
}
