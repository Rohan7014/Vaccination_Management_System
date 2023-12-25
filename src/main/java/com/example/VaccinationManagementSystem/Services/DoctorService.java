package com.example.VaccinationManagementSystem.Services;

import com.example.VaccinationManagementSystem.Dtos.RequestDtos.AssociateDoctorDtos;
import com.example.VaccinationManagementSystem.Dtos.RequestDtos.UpdateByEmailDto;
import com.example.VaccinationManagementSystem.Enums.Gender;
import com.example.VaccinationManagementSystem.Exceptions.*;
import com.example.VaccinationManagementSystem.Models.Doctor;
import com.example.VaccinationManagementSystem.Models.VaccinationCenter;
import com.example.VaccinationManagementSystem.Repository.DoctorRepository;
import com.example.VaccinationManagementSystem.Repository.VaccinationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class DoctorService {
    @Autowired
    DoctorRepository doctorRepository;
    @Autowired
    VaccinationRepository vaccinationRepository;
    public String addDoctor(Doctor doctor) throws DoctorAlreadyExistsException, EmailIdEmptyException {
        // Validation
        if(doctor.getEmailId().length()==0){
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

    public List<String> getdoctorGreaterThan() {
       // Doctor doctor=new Doctor();
        List<Doctor> doctorList =doctorRepository.findAll();
        List<String> docName=new ArrayList<>();
        for(Doctor doctor1:doctorList){
            if(doctor1.getAppointmentList().size()>=2){
                docName.add(doctor1.getName());
            }
        }
        return docName;
    }

    public List<String> getAllMaleDoctor() {
       // Doctor doctor=new Doctor();
        List<Doctor> doctorList =doctorRepository.findAll();
        List<String> docName=new ArrayList<>();
        for(Doctor doctor1:doctorList){
            if(doctor1.getGender()== Gender.Male) {
                if (doctor1.getAge() >= 40) {
                    docName.add(doctor1.getName());
                }
            }
        }
        return docName;
    }

    public String getRatio() {
       // Doctor doctor=new Doctor();
        List<Doctor> doctorList =doctorRepository.findAll();
      //  List<String> docName=new ArrayList<>();
        float male=0,female=0;
        for(Doctor doctor1:doctorList){
            if(doctor1.getGender()==Gender.Male){
                male++;
            }else{
                female++;
            }
        }
        String ans=ratio(male,female);
        return (ans);
    }
    float gcd(float p, float q) {
        if (q == 0) return p;
        else return gcd(q, p % q);
    }

    String ratio(float a, float b) {
        final float gcd = gcd(a,b);
        return a/gcd +" : "+b/gcd;
    }

    public String updateByEmail(UpdateByEmailDto updateByEmailDto)throws EmailNotFound {
        String emailId=updateByEmailDto.getEmailId();
        if(emailId.length()==0){
            throw new EmailNotFound("EmailId Is Empty");
        }
        Doctor doctor=new Doctor();
        List<Doctor> doctorList =doctorRepository.findAll();
        for(Doctor doctor1:doctorList){
            if(doctor1.getEmailId().equals(emailId)){
                doctor=doctor1;
            }
        }
        if(doctor==null){
            throw new EmailNotFound("EmailId Not Match, Please Enter Correct EmailId");
        }
        doctor.setAge(updateByEmailDto.getAge());
        doctor.setGender(updateByEmailDto.getGender());
        doctor.setName(updateByEmailDto.getName());
        doctorRepository.save(doctor);
        return "Details update Successfully";
    }
}