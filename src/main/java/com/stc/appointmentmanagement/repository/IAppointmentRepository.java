package com.stc.appointmentmanagement.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.stc.appointmentmanagement.entity.Appointment;
import com.stc.appointmentmanagement.entity.Patient;

@Repository
public interface IAppointmentRepository extends JpaRepository<Appointment, Integer> , JpaSpecificationExecutor<Appointment>{

	List<Appointment> findByReservationDate(LocalDate reservationDate);
	List<Appointment> findByReservationDateAndPatient(LocalDate reservationDate, Patient patient);
	
}
