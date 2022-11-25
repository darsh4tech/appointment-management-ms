package com.stc.appointmentmanagement.dto;

import java.time.LocalDate;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class SearchCriteria {

	private LocalDate reservationDateFrom;
	private LocalDate reservationDateTo;
	private String patientName;
	
}
