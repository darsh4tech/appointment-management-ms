package com.stc.appointmentmanagement.dto;

import java.io.Serializable;

import javax.validation.constraints.NotBlank;

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
public class PatientDto implements Serializable{

	private static final long serialVersionUID = 1L;

	@NotBlank 
	private String patientName;
	@NotBlank
	private String mobile;
	
}
