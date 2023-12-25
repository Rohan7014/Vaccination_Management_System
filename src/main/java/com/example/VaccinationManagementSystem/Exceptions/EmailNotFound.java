package com.example.VaccinationManagementSystem.Exceptions;

public class EmailNotFound extends RuntimeException{
    public EmailNotFound(String message) {
        super(message);
    }
}
