package com.example.VaccinationManagementSystem.Models;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import java.util.Date;

@Entity
@Table(name="dose")
@Data
public class Dose {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(unique = true)
    private String doseId;
    @CreationTimestamp
    private Date vaccinationDate;
    @OneToOne
    @JoinColumn
    private User user;

//    public User getUser() {
//        return user;
//    }
//
//    public void setUser(User user) {
//        this.user = user;
//    }
//
//    public int getId() {
//        return id;
//    }
//
//    public void setId(int id) {
//        this.id = id;
//    }
//
//    public String getDoseId() {
//        return doseId;
//    }
//
//    public void setDoseId(String doseId) {
//        this.doseId = doseId;
//    }
//
//    public Date getVaccinationDate() {
//        return vaccinationDate;
//    }
//
//    public void setVaccinationDate(Date vaccinationDate) {
//        this.vaccinationDate = vaccinationDate;
//    }
}