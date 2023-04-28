package com.campos.thiago.booking.exception;

public class GuestNotFoundException extends Exception {

	private static final long serialVersionUID = -7196999462005251922L;

	private static final String MESSAGE = "Guest not found. Please, use a registered visitor or make a new registration.";
	
	public GuestNotFoundException(){
        super(MESSAGE);
    }

    public GuestNotFoundException(Throwable cause){
        super(MESSAGE, cause);
    }
}
