package com.example.VaccinationManagementSystem.Services;

import com.example.VaccinationManagementSystem.Models.Dose;
import com.example.VaccinationManagementSystem.Models.User;
import com.example.VaccinationManagementSystem.Repository.DoseRepository;
import com.example.VaccinationManagementSystem.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class DoseService {
    @Autowired
    UserRepository userRepository;
    @Autowired
    DoseRepository doseRepository;

    public String giveDose1(String doseId, Integer userId) {
        User user=userRepository.findById(userId).get();

        // An Entity of that Model has been Created
        // This Entity will be saved in the database
        Dose dose = new Dose();

        // Setting its Attributes
        dose.setDoseId(doseId);
        dose.setUser(user);

        user.setDose(dose);
        // Save the Entity
        userRepository.save(user);
        return "Dose Given to User Successfully";
    }
}
