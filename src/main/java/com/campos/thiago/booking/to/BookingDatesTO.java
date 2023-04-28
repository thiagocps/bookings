package com.campos.thiago.booking.to;

import java.time.LocalDate;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class BookingDatesTO {
	
	@NotNull(message = "newStartDate is mandatory")
	private LocalDate newStartDate;
	
	@NotNull(message = "newEndDate is mandatory")
	private LocalDate newEndDate;
}
