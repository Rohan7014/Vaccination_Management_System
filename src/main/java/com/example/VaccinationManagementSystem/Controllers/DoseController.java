package com.example.VaccinationManagementSystem.Controllers;

import com.example.VaccinationManagementSystem.Models.User;
import com.example.VaccinationManagementSystem.Services.DoseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@RestController
@RequestMapping("/dose")
public class DoseController {
    @Autowired
    DoseService doseService;
    @PostMapping("/giveDose1")
    public String giveDose1(@RequestParam("doseId")String doseId,@RequestParam("userId")Integer userId){
        return doseService.giveDose1(doseId,userId);
    }

}
