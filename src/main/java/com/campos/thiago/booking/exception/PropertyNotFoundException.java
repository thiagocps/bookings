package com.campos.thiago.booking.exception;

public class PropertyNotFoundException extends Exception {

	private static final long serialVersionUID = -8500831330625282295L;
	
	private static final String MESSAGE = "Property entered cannot be found.";
	
	public PropertyNotFoundException(){
        super(MESSAGE);
    }

    public PropertyNotFoundException(Throwable cause){
        super(MESSAGE, cause);
    }

}
