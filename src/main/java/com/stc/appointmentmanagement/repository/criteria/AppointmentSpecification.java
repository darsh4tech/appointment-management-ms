package com.stc.appointmentmanagement.repository.criteria;

import java.time.LocalDate;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

import com.stc.appointmentmanagement.dto.SearchCriteria;
import com.stc.appointmentmanagement.entity.Appointment;

import lombok.AllArgsConstructor;
import lombok.Builder;

@Builder
@AllArgsConstructor
public class AppointmentSpecification implements Specification<Appointment> {

	private static final long serialVersionUID = 1L;

	private SearchCriteria searchCriteria;

	private static final String RESERVATION_DATE = "reservationDate";
	private static final String PATIENT = "patient";
	private static final String PATIENT_NAME = "patientName";
	
	@Override
	public Predicate toPredicate(Root<Appointment> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {

		Predicate predicate = criteriaBuilder.conjunction();

		filterDate(root, criteriaBuilder, predicate, RESERVATION_DATE,
				searchCriteria.getReservationDateFrom(), searchCriteria.getReservationDateTo());

		if (searchCriteria.getPatientName() != null) {
			predicate.getExpressions().add(criteriaBuilder.and(
					criteriaBuilder.like(criteriaBuilder.lower(root.get(PATIENT).get(PATIENT_NAME)), "%" + searchCriteria.getPatientName().toLowerCase() + "%")));

		}

		return predicate;
	}

	public static void filterDate(Root<?> root, CriteriaBuilder criteriaBuilder, Predicate predicate,
			String dateFieldName, LocalDate reservationDateFrom, LocalDate reservationDateTo) {
		if (reservationDateFrom != null && reservationDateTo != null) {

			predicate.getExpressions().add(criteriaBuilder
					.and(criteriaBuilder.between(root.get(dateFieldName), reservationDateFrom, reservationDateTo)));
		} else if (reservationDateFrom != null) {

			predicate.getExpressions().add(
					criteriaBuilder.and(criteriaBuilder.greaterThanOrEqualTo(root.get(dateFieldName), reservationDateFrom)));

		} else if (reservationDateTo != null) {

			predicate.getExpressions()
					.add(criteriaBuilder.and(criteriaBuilder.lessThanOrEqualTo(root.get(dateFieldName), reservationDateTo)));
		}
	}
}
