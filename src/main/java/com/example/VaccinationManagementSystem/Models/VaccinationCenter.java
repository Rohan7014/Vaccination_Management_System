package com.example.VaccinationManagementSystem.Models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="vaccination_centre")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class VaccinationCenter {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String centerName;
    private String address;
    private int dosCapacity;
    private LocalTime openingTime;
    private LocalTime closingTime;
    @OneToMany(mappedBy = "vaccinationCenter",cascade = CascadeType.ALL)
    private List<Doctor> doctorList=new ArrayList<>();
}
