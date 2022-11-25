package com.stc.appointmentmanagement.advice.exception;

public class PatientNoyFoundException extends RuntimeException{

	private static final long serialVersionUID = 1L;

	public PatientNoyFoundException(String errMSG) {
		super(errMSG);
	}
	
	public PatientNoyFoundException(String errorMessage, Throwable err) {
        super(errorMessage, err);
    }
	
}
