package com.stc.appointmentmanagement.controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.stc.appointmentmanagement.dto.AppointmentDto;
import com.stc.appointmentmanagement.dto.SearchCriteria;
import com.stc.appointmentmanagement.dto.response.AppointmentDtoResponse;
import com.stc.appointmentmanagement.enumuration.CancellationReason;
import com.stc.appointmentmanagement.service.IAppointmentService;

@RestController
@RequestMapping("/appointments")
public class AppointmentController {

	private final IAppointmentService appointmentService;

	public AppointmentController(IAppointmentService appointmentService) {
		this.appointmentService = appointmentService;
	}

	@GetMapping
	public ResponseEntity<List<AppointmentDtoResponse>> listALlAppointments() {
		return ResponseEntity.ok(this.appointmentService.listALlAppointment());
	}

	@PostMapping
	public ResponseEntity<AppointmentDtoResponse> addAppointment(
			@RequestBody @Validated AppointmentDto appointmentDto) {
		return ResponseEntity.status(HttpStatus.CREATED).body(this.appointmentService.addAppointment(appointmentDto));
	}

	@DeleteMapping("/{appointmentId}")
	public ResponseEntity<String> cancelAppointment(@PathVariable Integer appointmentId,
			@RequestParam CancellationReason reason) {
		this.appointmentService.cancelAppointment(appointmentId, reason);
		return ResponseEntity.ok("SUCESS");
	}

	@GetMapping("/search")
	public ResponseEntity<List<AppointmentDtoResponse>> searchAppointment(
			@RequestParam(required = false) String reservationDateFrom,
			@RequestParam(required = false) String reservationDateTo,
			@RequestParam(required = false) String patientName) {
		
		SearchCriteria criteria = new SearchCriteria();
		if(reservationDateFrom != null) {
			criteria.setReservationDateFrom(LocalDate.parse(reservationDateFrom));
		}
		if(reservationDateTo != null) {
			criteria.setReservationDateTo(LocalDate.parse(reservationDateTo));
		}
		criteria.setPatientName(patientName);
		return ResponseEntity.ok(this.appointmentService.searchAppointment(criteria));
	}

}
