package com.stc.appointmentmanagement.service;

import java.util.List;

import com.stc.appointmentmanagement.dto.AppointmentDto;
import com.stc.appointmentmanagement.dto.SearchCriteria;
import com.stc.appointmentmanagement.dto.response.AppointmentDtoResponse;
import com.stc.appointmentmanagement.enumuration.CancellationReason;

public interface IAppointmentService {

	public List<AppointmentDtoResponse> listALlAppointment();
	public AppointmentDtoResponse addAppointment(AppointmentDto appointmentDto);
	public void cancelAppointment(Integer appointmentId, CancellationReason reason);
	public List<AppointmentDtoResponse> searchAppointment(SearchCriteria searchCriteria);
	
}
