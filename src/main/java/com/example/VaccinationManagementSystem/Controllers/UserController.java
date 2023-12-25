package com.example.VaccinationManagementSystem.Controllers;

import com.example.VaccinationManagementSystem.Dtos.RequestDtos.UpdateEmailDtos;
import com.example.VaccinationManagementSystem.Exceptions.EmailIdEmptyException;
import com.example.VaccinationManagementSystem.Exceptions.EmailNotFound;
import com.example.VaccinationManagementSystem.Exceptions.UserNotFound;
import com.example.VaccinationManagementSystem.Models.User;
import com.example.VaccinationManagementSystem.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    UserService userService;
    @PostMapping("/add")
    public ResponseEntity<String> addUser(@RequestBody User user) {
        try{
            String user1=userService.addUser(user);
            return new ResponseEntity<>(user1, HttpStatus.CREATED);
        }catch (Exception e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.NOT_FOUND);
        }
    }
    @GetMapping("/getVaccinationDate")
    public ResponseEntity<Date> getVaccinationDate(@RequestParam("userId")Integer userId){
            Date date=userService.getVaccinationDate(userId);
            return new ResponseEntity<>(date,HttpStatus.FOUND);
    }
    @PutMapping("/updateEmail")
    public ResponseEntity<String> updateEmail(@RequestBody UpdateEmailDtos updateEmailDtos){
        try{
            String mail=userService.updateEmail(updateEmailDtos);
            return new ResponseEntity<>(mail,HttpStatus.CREATED);
        }catch (UserNotFound e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.NOT_FOUND);
        }catch (EmailNotFound e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.NOT_FOUND);
        }

    }
    @GetMapping("/getByEmail/{emailId}")
    public ResponseEntity<User> getUserByEmail(@PathVariable("emailId") String email){
            User user= userService.getUserByEmail(email);
            return new ResponseEntity<>(user,HttpStatus.FOUND);
    }
}
