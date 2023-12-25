package com.example.VaccinationManagementSystem.Services;

import com.example.VaccinationManagementSystem.Exceptions.DoseIdIsNull;
import com.example.VaccinationManagementSystem.Exceptions.UserNotFound;
import com.example.VaccinationManagementSystem.Models.Dose;
import com.example.VaccinationManagementSystem.Models.User;
import com.example.VaccinationManagementSystem.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.Optional;

@Service
public class DoseService {
    @Autowired
    UserRepository userRepository;


    public String giveDose1(String doseId, Integer userId) throws UserNotFound ,DoseIdIsNull{
        Optional<User> userOptional=userRepository.findById(userId);
        if(userOptional.isEmpty()){
            throw new UserNotFound("User Id Is Wrong , User Not Found");
        }
        if(doseId==null){
            throw new DoseIdIsNull("Dose Id Is Empty");
        }
        User user=userOptional.get();
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
