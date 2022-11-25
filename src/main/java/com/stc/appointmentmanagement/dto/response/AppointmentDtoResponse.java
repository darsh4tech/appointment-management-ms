package com.stc.appointmentmanagement.dto.response;

import java.io.Serializable;
import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.stc.appointmentmanagement.dto.PatientDto;
import com.stc.appointmentmanagement.enumuration.CancellationReason;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class AppointmentDtoResponse implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private Integer appointmentId;
	private LocalDate reservationDate;
	private CancellationReason cancellationReason;
	private PatientDto patientDto;
}
