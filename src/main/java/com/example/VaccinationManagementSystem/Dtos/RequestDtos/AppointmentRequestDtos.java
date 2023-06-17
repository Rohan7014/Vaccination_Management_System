package com.example.VaccinationManagementSystem.Dtos.RequestDtos;

import jakarta.persistence.criteria.CriteriaBuilder;
import lombok.Data;

import java.time.LocalTime;
import java.util.Date;

@Data
public class AppointmentRequestDtos {
    private Integer docId;
    private Integer userId;
    private Date appointmentDate;
    private LocalTime appointmentTime;
}
