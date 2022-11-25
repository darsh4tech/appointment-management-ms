package com.stc.appointmentmanagement.dto.response;

import java.io.Serializable;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.stc.appointmentmanagement.dto.AppointmentDto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class PatientDtoResponse implements Serializable{

	private static final long serialVersionUID = 1L;

	private Integer patientId;
	private String patientName;
	private String mobile;
	
	private Set<AppointmentDto> appointments;
	
}
