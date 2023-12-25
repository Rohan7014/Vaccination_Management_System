package com.example.VaccinationManagementSystem.Dtos.RequestDtos;

import com.example.VaccinationManagementSystem.Enums.Gender;
import lombok.Data;

@Data
public class UpdateByEmailDto {
    private String name;
    private int age;
    private Gender gender;
    private String emailId;

}
