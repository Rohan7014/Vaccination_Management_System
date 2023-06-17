package com.example.VaccinationManagementSystem.Services;

import com.example.VaccinationManagementSystem.Exceptions.VaccinationAddressNotFound;
import com.example.VaccinationManagementSystem.Models.VaccinationCenter;
import com.example.VaccinationManagementSystem.Repository.VaccinationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VaccinationService {
    @Autowired
    VaccinationRepository vaccinationRepository;
    public String addVaccinationCenter(VaccinationCenter vaccinationCenter) throws VaccinationAddressNotFound{
        if(vaccinationCenter.getAddress()==null){
            throw new VaccinationAddressNotFound("Vaccination Center is Empty");
        }
        vaccinationRepository.save(vaccinationCenter);
        return "Vaccination center added at location"+vaccinationCenter.getAddress();
    }
}
