package com.stc.appointmentmanagement.entity;

import java.util.List;
import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table
@Entity
public class Patient {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer patientId;
	private String patientName;
	private String mobile;
	@OneToMany(mappedBy = "patient", fetch = FetchType.LAZY)
	private List<Appointment> appointments;
	
	@Override
	public int hashCode() {
		return Objects.hash(appointments, mobile, patientId, patientName);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Patient other = (Patient) obj;
		return Objects.equals(appointments, other.appointments) && Objects.equals(mobile, other.mobile)
				&& Objects.equals(patientId, other.patientId) && Objects.equals(patientName, other.patientName);
	}
	@Override
	public String toString() {
		return "Patient [patientId=" + patientId + ", patientName=" + patientName + ", mobile=" + mobile
				+ ", appointments=" + appointments + "]";
	}
	
}
