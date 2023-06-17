package com.example.VaccinationManagementSystem.Services;

import com.example.VaccinationManagementSystem.Dtos.RequestDtos.UpdateEmailDtos;
import com.example.VaccinationManagementSystem.Models.Dose;
import com.example.VaccinationManagementSystem.Models.User;
import com.example.VaccinationManagementSystem.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class UserService {
    @Autowired
    UserRepository userRepository;
    public User addUser(User user){
        user = userRepository.save(user);
        return user;
    }
    public Date getVaccinationDate(Integer userId) {
        User user=userRepository.findById(userId).get();

        Dose dose=user.getDose();
        return dose.getVaccinationDate();
    }

    public String updateEmail(UpdateEmailDtos updateEmailDtos) {
        int userId = updateEmailDtos.getUserId();
        User user=userRepository.findById(userId).get();
        // Modify new entity with new parameter
        user.setEmailId(updateEmailDtos.getNewEmailId());
        userRepository.save(user);
        return "Old mail is modify with new one "+updateEmailDtos.getNewEmailId();
    }

    public User getUserByEmail(String email) {
        User user=userRepository.findByEmailId(email);
        return user;
    }
}
