package com.example.projectmedilog;

public class Patient {
    String firstname;
    String lastname;
    String email;
    String password;
    String phone;

    public Patient(String firstname, String lastname, String email, String password, String phone) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.email = email;
        this.password = password;
        this.phone = phone;
    }
}
