package com.example.VaccinationManagementSystem.Controllers;

import com.example.VaccinationManagementSystem.Exceptions.DoseIdIsNull;
import com.example.VaccinationManagementSystem.Exceptions.UserNotFound;
import com.example.VaccinationManagementSystem.Services.DoseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/dose")
public class DoseController {
    @Autowired
    DoseService doseService;
    @PostMapping("/giveDose1")
    public ResponseEntity<String> giveDose1(@RequestParam("doseId")String doseId, @RequestParam("userId")Integer userId){
        try{
            String result=doseService.giveDose1(doseId,userId);
            return new ResponseEntity<>(result, HttpStatus.OK);
        }catch (UserNotFound e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.NOT_FOUND);
        }catch (DoseIdIsNull e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.NOT_FOUND);
        }
    }
}
