package com.campos.thiago.booking.exception;

public class BookingNotFoundException extends Exception {

	private static final long serialVersionUID = -7196999462005251922L;

	private static final String MESSAGE = "Booking selected not found.";
	
	public BookingNotFoundException(){
        super(MESSAGE);
    }

    public BookingNotFoundException(Throwable cause){
        super(MESSAGE, cause);
    }
}
