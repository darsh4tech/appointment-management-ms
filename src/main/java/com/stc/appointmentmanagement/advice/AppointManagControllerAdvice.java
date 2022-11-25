package com.stc.appointmentmanagement.advice;

import java.time.Instant;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import com.stc.appointmentmanagement.advice.exception.AppointmentAddFailedException;
import com.stc.appointmentmanagement.advice.exception.DuplicateFoundException;
import com.stc.appointmentmanagement.advice.exception.PatientNoyFoundException;

import lombok.extern.slf4j.Slf4j;

@ControllerAdvice
public class AppointManagControllerAdvice {

	@ExceptionHandler(Exception.class)
	ResponseEntity<ApiError> handleStatusException(Exception ex, WebRequest request) {
		return ResponseEntity
				.status(HttpStatus.INTERNAL_SERVER_ERROR)
				.body(ApiError.builder().status(HttpStatus.INTERNAL_SERVER_ERROR)
						.message(ex.getMessage())
						.timestamp(Instant.now())
						.build());
	}
	
	@ExceptionHandler(DuplicateFoundException.class)
	ResponseEntity<ApiError> handleDuplicateFoundException(DuplicateFoundException ex, WebRequest request) {
		return ResponseEntity
				.status(HttpStatus.INTERNAL_SERVER_ERROR)
				.body(ApiError.builder().status(HttpStatus.BAD_REQUEST)
						.message(ex.getMessage())
						.timestamp(Instant.now())
						.build());
	}

	@ExceptionHandler(AppointmentAddFailedException.class)
	ResponseEntity<ApiError> handleAppointmentAddFailedException(AppointmentAddFailedException ex, WebRequest request) {
		return ResponseEntity
				.status(HttpStatus.INTERNAL_SERVER_ERROR)
				.body(ApiError.builder().status(HttpStatus.BAD_REQUEST)
						.message(ex.getMessage())
						.timestamp(Instant.now())
						.build());
	}

	@ExceptionHandler(PatientNoyFoundException.class)
	ResponseEntity<ApiError> handlePatientNoyFoundException(PatientNoyFoundException ex, WebRequest request) {
		return ResponseEntity
				.status(HttpStatus.INTERNAL_SERVER_ERROR)
				.body(ApiError.builder().status(HttpStatus.BAD_REQUEST)
						.message(ex.getMessage())
						.timestamp(Instant.now())
						.build());
	}

}
