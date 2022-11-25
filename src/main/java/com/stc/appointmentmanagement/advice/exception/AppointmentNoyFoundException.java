package com.stc.appointmentmanagement.advice.exception;

public class AppointmentNoyFoundException extends RuntimeException{

	private static final long serialVersionUID = 1L;

	public AppointmentNoyFoundException(String errMSG) {
		super(errMSG);
	}
	
	public AppointmentNoyFoundException(String errorMessage, Throwable err) {
        super(errorMessage, err);
    }
	
}
