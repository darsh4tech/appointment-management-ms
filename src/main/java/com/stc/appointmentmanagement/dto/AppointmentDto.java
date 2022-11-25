package com.stc.appointmentmanagement.dto;

import java.io.Serializable;
import java.time.LocalDate;

import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class AppointmentDto implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@NotNull
	private LocalDate reservationDate;
	@NotNull
	private Integer patientId;
}
