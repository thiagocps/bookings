package com.campos.thiago.booking.exception;

public class PeriodNotAvailableException extends Exception {

	private static final long serialVersionUID = 2324035676051350884L;
	
	private static final String MESSAGE = "Period selected not available.";
	
	public PeriodNotAvailableException(){
        super(MESSAGE);
    }

    public PeriodNotAvailableException(Throwable cause){
        super(MESSAGE, cause);
    }

}
