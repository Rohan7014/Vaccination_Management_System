package com.example.VaccinationManagementSystem.Controllers;

import com.example.VaccinationManagementSystem.Dtos.RequestDtos.AssociateDoctorDtos;
import com.example.VaccinationManagementSystem.Exceptions.CenterNotFound;
import com.example.VaccinationManagementSystem.Exceptions.VaccinationAddressNotFound;
import com.example.VaccinationManagementSystem.Models.VaccinationCenter;
import com.example.VaccinationManagementSystem.Services.VaccinationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/vaccinationcenter")
public class VaccinationController {
    @Autowired
    VaccinationService vaccinationService;
    @PostMapping("/add")
    public ResponseEntity<String> addCenter(@RequestBody VaccinationCenter vaccinationCenter){
        try{
            String result=vaccinationService.addVaccinationCenter(vaccinationCenter);
            return new ResponseEntity<>(result, HttpStatus.OK);
        }catch (VaccinationAddressNotFound e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.NOT_FOUND);
        }
    }
    // New API
    @GetMapping("/getAllDoctors")
    public ResponseEntity<?> getAllDoctors(@RequestParam("centerId")Integer centerId){
        try{
            List<String> doctorsList=vaccinationService.getAllDoctors(centerId);
            return new ResponseEntity<>(doctorsList,HttpStatus.FOUND);
        }catch (CenterNotFound e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.NOT_FOUND);
        }
    }
    @GetMapping("/getAllMaleDoctors")
    public ResponseEntity<?> getAllMaleDoctors(@RequestParam("centerId")Integer centerId){
        try{
            List<String> doctorsList=vaccinationService.getAllMaleDoctors(centerId);
            return new ResponseEntity<>(doctorsList,HttpStatus.FOUND);
        }catch (CenterNotFound e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.NOT_FOUND);
        }
    }
    @GetMapping("/getAllFemaleDoctors")
    public ResponseEntity<?> getAllFemaleDoctors(@RequestParam("centerId")Integer centerId){
        try{
            List<String> doctorsList=vaccinationService.getAllFemaleDoctors(centerId);
            return new ResponseEntity<>(doctorsList,HttpStatus.FOUND);
        }catch (CenterNotFound e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.NOT_FOUND);
        }
    }
    @GetMapping("/getAllMaleDoctorsGreater")
    public ResponseEntity<?> getAllMaleDoctorsGreater(@RequestParam("centerId")Integer centerId){
        try{
            List<String> doctorsList=vaccinationService.getAllMaleDoctorsGreater(centerId);
            return new ResponseEntity<>(doctorsList,HttpStatus.FOUND);
        }catch (CenterNotFound e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.NOT_FOUND);
        }
    }
}
