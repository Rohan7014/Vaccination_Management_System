package com.example.VaccinationManagementSystem.Services;

import com.example.VaccinationManagementSystem.Dtos.RequestDtos.AppointmentRequestDtos;
import com.example.VaccinationManagementSystem.Exceptions.DoctorNotFound;
import com.example.VaccinationManagementSystem.Exceptions.UserNotFound;
import com.example.VaccinationManagementSystem.Models.Appointment;
import com.example.VaccinationManagementSystem.Models.Doctor;
import com.example.VaccinationManagementSystem.Models.User;
import com.example.VaccinationManagementSystem.Repository.AppointmentRepository;
import com.example.VaccinationManagementSystem.Repository.DoctorRepository;
import com.example.VaccinationManagementSystem.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AppointmentService {
    @Autowired
    private AppointmentRepository appointmentRepository;
    @Autowired
    private DoctorRepository doctorRepository;
    @Autowired
    private UserRepository userRepository;
    public String addAppointment(AppointmentRequestDtos appointmentRequestDtos)throws DoctorNotFound, UserNotFound {
        Optional<Doctor> doctorOptional=doctorRepository.findById(appointmentRequestDtos.getDocId());
        if(!doctorOptional.isPresent()){
            throw new DoctorNotFound("DoctorId Not Found");
        }
        Optional<User> userOptional=userRepository.findById(appointmentRequestDtos.getUserId());
        if(!userOptional.isPresent()){
            throw new UserNotFound("UserId Not Found");
        }
        Doctor doctor=doctorOptional.get();
        User user=userOptional.get();
        Appointment appointment=new Appointment();
        // Creating The Object and Setting Its Attribute
        appointment.setAppointmentDate(appointmentRequestDtos.getAppointmentDate());
        appointment.setAppointmentTime((appointmentRequestDtos.getAppointmentTime()));
        // Setting the Foreign Key Attribute
        appointment.setDoctor(doctor);
        appointment.setUser(user);
        //Method 1 and Its used Prevelant
        // Saving it before so that I can get the Primary Key of the Appointment Table
        appointment=appointmentRepository.save(appointment);
        // Method 2
        // get the Appointment Id form the Last Doctor
//        List<Appointment> appointmentList=doctor.getAppointmentList();
//        Appointment latestApp=appointmentList.get(appointmentList.size()-1);
//        int id=latestApp.getId();

        doctor.getAppointmentList().add(appointment);

        user.getAppointmentList().add(appointment);

        doctorRepository.save(doctor);
        userRepository.save(user);
        return "Appointment Booked Successfully";
    }
}
