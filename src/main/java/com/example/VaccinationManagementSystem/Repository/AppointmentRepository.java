package com.example.VaccinationManagementSystem.Repository;

import com.example.VaccinationManagementSystem.Models.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AppointmentRepository extends JpaRepository<Appointment,Integer> {

}
