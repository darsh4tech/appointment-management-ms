package com.stc.appointmentmanagement.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.stc.appointmentmanagement.entity.Patient;

@Repository
public interface IPatientRepository extends JpaRepository<Patient, Integer>{

	List<Patient> findByPatientName(String patientName);
	
}
