package com.example.VaccinationManagementSystem.Controllers;

import com.example.VaccinationManagementSystem.Dtos.RequestDtos.AppointmentRequestDtos;
import com.example.VaccinationManagementSystem.Services.AppointmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/appointment")
public class AppointmentController {
    @Autowired
    AppointmentService appointmentService;
    @PostMapping("/book")
    public String addAppointment(@RequestBody AppointmentRequestDtos appointmentRequestDtos){
        try{
            String result =appointmentService.addAppointment(appointmentRequestDtos);
            return result;
        }catch(Exception e){
            return e.getMessage();
        }
    }
}
