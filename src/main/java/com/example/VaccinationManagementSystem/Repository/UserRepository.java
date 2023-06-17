package com.example.VaccinationManagementSystem.Repository;

import com.example.VaccinationManagementSystem.Models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User,Integer> {
    //just define the function to execute
    public User findByEmailId(String emailId);
    // prebuilt function : and you can use directly
}
