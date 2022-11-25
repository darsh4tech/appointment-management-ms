package com.stc.appointmentmanagement.advice.exception;

public class DuplicateFoundException extends RuntimeException{

	private static final long serialVersionUID = 1L;

	public DuplicateFoundException(String errMSG) {
		super(errMSG);
	}
	
	public DuplicateFoundException(String errorMessage, Throwable err) {
        super(errorMessage, err);
    }
	
}
