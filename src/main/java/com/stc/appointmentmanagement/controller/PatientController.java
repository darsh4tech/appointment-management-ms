package com.stc.appointmentmanagement.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.stc.appointmentmanagement.dto.PatientDto;
import com.stc.appointmentmanagement.dto.response.PatientDtoResponse;
import com.stc.appointmentmanagement.service.IPatientService;

@RestController
@RequestMapping("/patients")
public class PatientController {

	private final IPatientService patientService;
	
	public PatientController(IPatientService patientService) {
		this.patientService = patientService;
	}
	
	@PostMapping
	public ResponseEntity<PatientDtoResponse> addPatient(@RequestBody @Validated PatientDto patientDto) {
		return ResponseEntity.status(HttpStatus.CREATED).body(this.patientService.addPatient(patientDto));
	}
	
	@GetMapping("/{patientId}")
	public ResponseEntity<PatientDtoResponse> getPatient(@PathVariable Integer patientId) {
		return ResponseEntity.ok(this.patientService.getPatientWithHisotry(patientId));
	}

	
}
