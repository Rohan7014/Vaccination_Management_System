package com.example.VaccinationManagementSystem.Services;

import com.example.VaccinationManagementSystem.Dtos.RequestDtos.AssociateDoctorDtos;
import com.example.VaccinationManagementSystem.Exceptions.CenterNotFound;
import com.example.VaccinationManagementSystem.Exceptions.DoctorAlreadyExistsException;
import com.example.VaccinationManagementSystem.Exceptions.DoctorNotFound;
import com.example.VaccinationManagementSystem.Exceptions.EmailIdEmptyException;
import com.example.VaccinationManagementSystem.Models.Doctor;
import com.example.VaccinationManagementSystem.Models.VaccinationCenter;
import com.example.VaccinationManagementSystem.Repository.DoctorRepository;
import com.example.VaccinationManagementSystem.Repository.VaccinationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class DoctorService {
    @Autowired
    DoctorRepository doctorRepository;
    @Autowired
    VaccinationRepository vaccinationRepository;
    public String addDoctor(Doctor doctor) throws DoctorAlreadyExistsException, EmailIdEmptyException {
        // Validation
        if(doctor.getEmailId()=="null"){
            throw new EmailIdEmptyException("Email Id Is Mandatory.");
        }
        if(doctorRepository.findByEmailId(doctor.getEmailId())!=null){
            throw new DoctorAlreadyExistsException("Doctor With This Email ID is Already Exists.");
        }
        doctorRepository.save(doctor);
        return "Doctor Has Been Added In The Database.";
    }

    public String associateDoctor(AssociateDoctorDtos associateDoctorDtos) throws DoctorNotFound, CenterNotFound {
        Integer docId=associateDoctorDtos.getDocId();
        Optional<Doctor> doctorOpt=doctorRepository.findById(docId);
        if(!doctorOpt.isPresent()){
            throw new DoctorNotFound("Doctor Id is Wrong");
        }
        Integer centerId=associateDoctorDtos.getCenterId();
        Optional<VaccinationCenter>centerOpt=vaccinationRepository.findById(centerId);
        if(!centerOpt.isPresent()){
            throw new CenterNotFound("Center Id is Incorrect");
        }
        Doctor doctor=doctorOpt.get();
        VaccinationCenter vaccinationCenter=centerOpt.get();
        // Setting Foreign Key
        doctor.setVaccinationCenter(vaccinationCenter);
        // Set the Bidirectional Mapping
        // Adding this Doctor to the List Of Doctor of that Vaccination Center
        vaccinationCenter.getDoctorList().add(doctor);
        vaccinationRepository.save(vaccinationCenter);
        return "Doctor has been Associated to Center";
    }
}