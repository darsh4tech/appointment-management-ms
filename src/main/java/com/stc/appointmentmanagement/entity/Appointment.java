package com.stc.appointmentmanagement.entity;

import java.time.LocalDate;
import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.stc.appointmentmanagement.enumuration.CancellationReason;

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
public class Appointment {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer appointmentId;
	private LocalDate reservationDate;
	@Enumerated(EnumType.STRING)
	private CancellationReason cancellationReason;
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "patient_id", nullable = false)
	private Patient patient;
	
	@Override
	public int hashCode() {
		return Objects.hash(appointmentId, cancellationReason, patient, reservationDate);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Appointment other = (Appointment) obj;
		return Objects.equals(appointmentId, other.appointmentId) && cancellationReason == other.cancellationReason
				&& Objects.equals(patient, other.patient) && Objects.equals(reservationDate, other.reservationDate);
	}
	@Override
	public String toString() {
		return "Appointment [appointmentId=" + appointmentId + ", reservationDate=" + reservationDate
				+ ", cancellationReason=" + cancellationReason + ", patient=" + patient + "]";
	}
	
}
