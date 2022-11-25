package com.stc.appointmentmanagement.advice.exception;

public class AppointmentAddFailedException extends RuntimeException{

	private static final long serialVersionUID = 1L;

	public AppointmentAddFailedException(String errMSG) {
		super(errMSG);
	}
	
	public AppointmentAddFailedException(String errorMessage, Throwable err) {
        super(errorMessage, err);
    }
	
}
