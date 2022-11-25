package com.stc.appointmentmanagement.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.stc.appointmentmanagement.advice.exception.DuplicateFoundException;
import com.stc.appointmentmanagement.advice.exception.PatientNoyFoundException;
import com.stc.appointmentmanagement.dto.PatientDto;
import com.stc.appointmentmanagement.dto.response.PatientDtoResponse;
import com.stc.appointmentmanagement.entity.Patient;
import com.stc.appointmentmanagement.repository.IPatientRepository;
import com.stc.appointmentmanagement.service.IPatientService;
import com.stc.appointmentmanagement.utils.ObjectMapperUtils;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class PatientServiceImpl implements IPatientService {

	private final IPatientRepository patientRepository;
	private final ObjectMapperUtils objectMapperUtils;

	public PatientServiceImpl(IPatientRepository patientRepository, ObjectMapperUtils objectMapperUtils) {
		this.patientRepository = patientRepository;
		this.objectMapperUtils = objectMapperUtils;
	}

	@Override
	@Transactional(readOnly = true)
	public PatientDtoResponse getPatientWithHisotry(Integer patientId) {
		PatientDtoResponse patientDto = null;
		try {
			// validate if patient id exist
			Optional<Patient> findByIdOptional = this.patientRepository.findById(patientId);
			if (findByIdOptional.isEmpty()) {
				throw new PatientNoyFoundException(String.format("provided patient id %s not exist", patientId));
			}
			patientDto = this.objectMapperUtils.map(findByIdOptional.get(), PatientDtoResponse.class);

			log.info("patientDto: {}", patientDto);
		} catch (Exception e) {
			log.error("getPatientWithHisAppointment - Error: {}", e);
			throw e;
		}
		return patientDto;
	}

	@Override
	@Transactional
	public PatientDtoResponse addPatient(PatientDto patientDto) {

		PatientDtoResponse patientDtoBacked = null;
		try {
			// validate if patient name exist before
			List<Patient> list = this.patientRepository.findByPatientName(patientDto.getPatientName());
			if (!list.isEmpty()) {
				throw new DuplicateFoundException("patient name already Exist");
			}
			// add patient logic
			Patient patient = this.objectMapperUtils.map(patientDto, Patient.class);
			patientDtoBacked = this.objectMapperUtils.map(this.patientRepository.save(patient),
					PatientDtoResponse.class);
		} catch (Exception e) {
			log.error("addPatient - Error: {}", e);
			throw e;
		}
		return patientDtoBacked;
	}

	@Override
	public Patient findPatient(Integer patientId) {
		Optional<Patient> findByIdOptional = this.patientRepository.findById(patientId);
		if (findByIdOptional.isEmpty()) {
			throw new PatientNoyFoundException(String.format("provided patient id %s not exist", patientId));
		}
		return findByIdOptional.get();
	}

}
