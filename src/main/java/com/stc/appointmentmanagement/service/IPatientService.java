package com.stc.appointmentmanagement.service;

import com.stc.appointmentmanagement.dto.PatientDto;
import com.stc.appointmentmanagement.dto.response.PatientDtoResponse;
import com.stc.appointmentmanagement.entity.Patient;

public interface IPatientService {

	public PatientDtoResponse addPatient(PatientDto patientDto);
	public PatientDtoResponse getPatientWithHisotry(Integer patientId);
	public Patient findPatient(Integer patientId);
}
