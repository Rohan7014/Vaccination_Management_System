package com.example.VaccinationManagementSystem.Services;

import com.example.VaccinationManagementSystem.Enums.Gender;
import com.example.VaccinationManagementSystem.Exceptions.CenterNotFound;
import com.example.VaccinationManagementSystem.Exceptions.VaccinationAddressNotFound;
import com.example.VaccinationManagementSystem.Models.Doctor;
import com.example.VaccinationManagementSystem.Models.VaccinationCenter;
import com.example.VaccinationManagementSystem.Repository.VaccinationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class VaccinationService {
    @Autowired
    VaccinationRepository vaccinationRepository;
    public String addVaccinationCenter(VaccinationCenter vaccinationCenter) throws VaccinationAddressNotFound{
        if(vaccinationCenter.getAddress()==null){
            throw new VaccinationAddressNotFound("Vaccination Center is Empty");
        }
        vaccinationRepository.save(vaccinationCenter);
        return "Vaccination center added at location "+vaccinationCenter.getAddress();
    }

    public List<String> getAllDoctors(Integer centerId) throws CenterNotFound {
        Optional<VaccinationCenter> centerOptional = vaccinationRepository.findById(centerId);
        if(centerOptional.isEmpty()){
            throw new CenterNotFound("CenterId Is Incorrect , Center Not Found");
        }
        VaccinationCenter center=centerOptional.get();
        List<Doctor> docotrsList=new ArrayList<>();
        docotrsList=center.getDoctorList();
        List<String> doctorsNames=new ArrayList<>();
        for(Doctor name:docotrsList){
            doctorsNames.add(name.getName());
        }
        return doctorsNames;
    }

    public List<String> getAllMaleDoctors(Integer centerId) throws CenterNotFound {
        Optional<VaccinationCenter> centerOptional = vaccinationRepository.findById(centerId);
        if(centerOptional.isEmpty()){
            throw new CenterNotFound("CenterId Is Incorrect , Center Not Found");
        }
        VaccinationCenter center=centerOptional.get();
        List<Doctor> docotrsList=center.getDoctorList();
        List<String> doctorsNames=new ArrayList<>();
        for(Doctor name:docotrsList){
            if(name.getGender()== Gender.Male) {
                doctorsNames.add(name.getName());
            }
        }
        return doctorsNames;
    }
    public List<String> getAllFemaleDoctors(Integer centerId) throws CenterNotFound {
        Optional<VaccinationCenter> centerOptional = vaccinationRepository.findById(centerId);
        if(centerOptional.isEmpty()){
            throw new CenterNotFound("CenterId Is Incorrect , Center Not Found");
        }
        VaccinationCenter center=centerOptional.get();
        List<Doctor> docotrsList=center.getDoctorList();
        List<String> doctorsNames=new ArrayList<>();
        for(Doctor name:docotrsList){
            if(name.getGender()== Gender.Female) {
                doctorsNames.add(name.getName());
            }
        }
        return doctorsNames;
    }

    public List<String> getAllMaleDoctorsGreater(Integer centerId) throws CenterNotFound {
        Optional<VaccinationCenter> centerOptional = vaccinationRepository.findById(centerId);
        if(centerOptional.isEmpty()){
            throw new CenterNotFound("CenterId Is Incorrect , Center Not Found");
        }
        VaccinationCenter center=centerOptional.get();
        List<Doctor> docotrsList=center.getDoctorList();
        List<String> doctorsNames=new ArrayList<>();
        for(Doctor name:docotrsList){
            if(name.getGender()== Gender.Male) {
                if(name.getAge()>=40) {
                    doctorsNames.add(name.getName());
                }
            }
        }
        return doctorsNames;
    }
}
