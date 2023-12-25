package com.example.VaccinationManagementSystem.Controllers;

import com.example.VaccinationManagementSystem.Dtos.RequestDtos.AppointmentRequestDtos;
import com.example.VaccinationManagementSystem.Services.AppointmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<String> addAppointment(@RequestBody AppointmentRequestDtos appointmentRequestDtos){
        try{
            String result =appointmentService.addAppointment(appointmentRequestDtos);
            return new ResponseEntity<>(result, HttpStatus.CREATED);
        }catch(Exception e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }
}
