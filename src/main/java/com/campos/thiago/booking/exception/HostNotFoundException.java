package com.campos.thiago.booking.exception;

public class HostNotFoundException extends Exception {

	private static final long serialVersionUID = -7196999462005251922L;

	private static final String MESSAGE = "Host not found.";
	
	public HostNotFoundException(){
        super(MESSAGE);
    }

    public HostNotFoundException(Throwable cause){
        super(MESSAGE, cause);
    }
}
