package com.example.VaccinationManagementSystem.Services;

import com.example.VaccinationManagementSystem.Dtos.RequestDtos.UpdateEmailDtos;
import com.example.VaccinationManagementSystem.Exceptions.EmailNotFound;
import com.example.VaccinationManagementSystem.Exceptions.UserNotFound;
import com.example.VaccinationManagementSystem.Models.Dose;
import com.example.VaccinationManagementSystem.Models.User;
import com.example.VaccinationManagementSystem.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    UserRepository userRepository;
    public String addUser(User user){
        userRepository.save(user);
        return "User Added Successfully";
    }
    public Date getVaccinationDate(Integer userId) {
        User user=userRepository.findById(userId).get();
        Dose dose=user.getDose();
        return dose.getVaccinationDate();
    }

    public String updateEmail(UpdateEmailDtos updateEmailDtos) throws UserNotFound,EmailNotFound {
        int userId = updateEmailDtos.getUserId();
        Optional<User> userOptional=userRepository.findById(userId);
        if(userOptional.isEmpty()){
            throw new UserNotFound("User Not Found");
        }
        User user=userOptional.get();
        // Modify new entity with new parameter
        if(updateEmailDtos.getNewEmailId()==null){
            throw new EmailNotFound("Email Not Found");
        }
        user.setEmailId(updateEmailDtos.getNewEmailId());
        userRepository.save(user);
        return "Old mail is modify with new one "+updateEmailDtos.getNewEmailId();
    }

    public User getUserByEmail(String email) {
        return userRepository.findByEmailId(email);
    }
}
