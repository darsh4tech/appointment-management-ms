package com.stc.appointmentmanagement.service.impl;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.stc.appointmentmanagement.advice.exception.AppointmentNoyFoundException;
import com.stc.appointmentmanagement.dto.AppointmentDto;
import com.stc.appointmentmanagement.dto.PatientDto;
import com.stc.appointmentmanagement.dto.SearchCriteria;
import com.stc.appointmentmanagement.dto.response.AppointmentDtoResponse;
import com.stc.appointmentmanagement.entity.Appointment;
import com.stc.appointmentmanagement.entity.Patient;
import com.stc.appointmentmanagement.enumuration.CancellationReason;
import com.stc.appointmentmanagement.repository.IAppointmentRepository;
import com.stc.appointmentmanagement.repository.criteria.AppointmentSpecification;
import com.stc.appointmentmanagement.service.IAppointmentService;
import com.stc.appointmentmanagement.service.IPatientService;
import com.stc.appointmentmanagement.utils.ObjectMapperUtils;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@Transactional
public class AppointmentServiceImpl implements IAppointmentService {

	private final IAppointmentRepository appointmentRepository;
	private final ObjectMapperUtils objectMapperUtils;
	private final IPatientService patientService;

	public AppointmentServiceImpl(IAppointmentRepository appointmentRepository, ObjectMapperUtils objectMapperUtils,
			IPatientService patientService) {
		this.appointmentRepository = appointmentRepository;
		this.objectMapperUtils = objectMapperUtils;
		this.patientService = patientService;
	}

	@Override
	@Transactional(readOnly = true)
	public List<AppointmentDtoResponse> listALlAppointment() {

		List<AppointmentDtoResponse> response = new ArrayList<>();
		try {
			List<Appointment> appointments = this.appointmentRepository.findByReservationDate(LocalDate.now());
			appointments.stream().forEach(appoint -> {
				response.add(AppointmentDtoResponse.builder().cancellationReason(appoint.getCancellationReason())
						.appointmentId(appoint.getAppointmentId()).reservationDate(appoint.getReservationDate())
						.patientDto(this.objectMapperUtils.map(appoint.getPatient(), PatientDto.class)).build());

			});
		} catch (Exception e) {
			log.error("listALlAppointment - Error: {}", e);
			throw e;
		}
		return response;
	}

	@Override
	public AppointmentDtoResponse addAppointment(AppointmentDto appointmentDto) {

		AppointmentDtoResponse appointmentDtoBacked = null;
		try {
			Patient patient = this.patientService.findPatient(appointmentDto.getPatientId());

			/*
			 * validate if this patient reserve an appointment in this day List<Appointment>
			 * appointments =
			 * this.appointmentRepository.findByReservationDateAndPatient(LocalDate.now(),
			 * patient); if (!appointments.isEmpty()) { throw new
			 * AppointmentAddFailedException(String
			 * .format("appointment already reserved for this patient [%s] today",
			 * patient.getPatientName())); }
			 */
			Appointment appoint = this.objectMapperUtils.map(appointmentDto, Appointment.class);
			appoint.setPatient(patient);
			appointmentDtoBacked = this.objectMapperUtils.map(this.appointmentRepository.save(appoint),
					AppointmentDtoResponse.class);
		} catch (Exception e) {
			log.error("addPatient - Error: {}", e);
			throw e;
		}
		return appointmentDtoBacked;
	}

	@Override
	public void cancelAppointment(Integer appointmentId, CancellationReason reason) {

		try {
			// validate if patient id exist
			Optional<Appointment> findByIdOptional = this.appointmentRepository.findById(appointmentId);
			if (findByIdOptional.isEmpty()) {
				throw new AppointmentNoyFoundException(
						String.format("provided appointment id %s not exist", appointmentId));
			}
			Appointment appointment = findByIdOptional.get();
			appointment.setCancellationReason(reason);
			this.appointmentRepository.save(appointment);

		} catch (Exception e) {
			log.error("addPatient - Error: {}", e);
			throw e;
		}

	}

	@Override
	@Transactional(readOnly = true)
	public List<AppointmentDtoResponse> searchAppointment(SearchCriteria searchCriteria) {
		List<AppointmentDtoResponse> response = new ArrayList<>();
		try {
			List<Appointment> appointments = this.appointmentRepository
					.findAll(AppointmentSpecification.builder().searchCriteria(searchCriteria).build());

			appointments.stream().forEach(appoint -> {
				response.add(AppointmentDtoResponse.builder().cancellationReason(appoint.getCancellationReason())
						.appointmentId(appoint.getAppointmentId()).reservationDate(appoint.getReservationDate())
						.patientDto(this.objectMapperUtils.map(appoint.getPatient(), PatientDto.class)).build());
			});
			return response;
		} catch (Exception e) {
			log.error("addPatient - Error: {}", e);
			throw e;
		}
	}

}
