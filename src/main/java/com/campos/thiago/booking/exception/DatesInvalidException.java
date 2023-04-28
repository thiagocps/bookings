package com.campos.thiago.booking.exception;

public class DatesInvalidException extends Exception {

	private static final long serialVersionUID = -7196999462005251922L;

	private static final String MESSAGE = "Invalids dates. Please review the information.";
	
	public DatesInvalidException(){
        super(MESSAGE);
    }

    public DatesInvalidException(Throwable cause){
        super(MESSAGE, cause);
    }
}
