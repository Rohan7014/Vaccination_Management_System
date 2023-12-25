package com.example.VaccinationManagementSystem.Controllers;

import com.example.VaccinationManagementSystem.Dtos.RequestDtos.AssociateDoctorDtos;
import com.example.VaccinationManagementSystem.Dtos.RequestDtos.UpdateByEmailDto;
import com.example.VaccinationManagementSystem.Exceptions.EmailNotFound;
import com.example.VaccinationManagementSystem.Models.Doctor;
import com.example.VaccinationManagementSystem.Services.DoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/doctor")
public class DoctorController {
    @Autowired
    DoctorService doctorService;
    @PostMapping("/add")
    public ResponseEntity<String> addDoctor(@RequestBody Doctor doctor){
        try{
            String response=doctorService.addDoctor(doctor);
            return new ResponseEntity<>(response,HttpStatus.CREATED);
        }catch (Exception e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.NOT_FOUND);
        }
    }
    @PostMapping("/associateWithCenter")
    public ResponseEntity<String> associateDoctor(@RequestBody AssociateDoctorDtos associateDoctorDtos){
        try{
            String result= doctorService.associateDoctor(associateDoctorDtos);
            return new ResponseEntity<>(result, HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.NOT_FOUND);
        }
    }
    //Give All The Doctors Who Have More Than 10 Appointment
    @GetMapping("/doctorsGreaterthan")
    public ResponseEntity<List<String>> getdoctorGreaterThan(){
        List<String> doctorList=doctorService.getdoctorGreaterThan();
        return new ResponseEntity<>(doctorList,HttpStatus.FOUND);
    }
    // Give All The Male Doctors Whose Age Is Greater Than 40
    @GetMapping("/maleDoctorsGreater")
    public ResponseEntity<List<String>> getAllMaleDoctor(){
        List<String> doctorList=doctorService.getAllMaleDoctor();
        return new ResponseEntity<>(doctorList,HttpStatus.FOUND);
    }
    //Get The Ratio Of Male To Female Doctors
    @GetMapping("/getRatio")
    public ResponseEntity<String> getRatio(){
        String result=doctorService.getRatio();
        return new ResponseEntity<>(result,HttpStatus.OK);
    }
    //Update The Details Based On emailId Of The Doctor.
    @PutMapping ("/updateDetailsBasedOnEmail")
    public ResponseEntity<String> updateDetailsByEmail(@RequestBody UpdateByEmailDto updateByEmailDto){
        try{
            String result=doctorService.updateByEmail(updateByEmailDto);
            return new ResponseEntity<>(result,HttpStatus.CREATED);
        }catch (EmailNotFound e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.NOT_FOUND);
        }
    }
}
