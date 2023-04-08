package com.example.projectmedilog;

public class pAppointmentTable {
    String name, email, gender, date, time, phone, injuryOrCondition, doctor, age;

    public  pAppointmentTable(String name, String email, String gender, String age, String date, String time, String phone, String doctor, String injuryOrCondition){
        this.name = name;
        this.email = email;
        this.gender = gender;
        this.age = age;
        this.date = date;
        this.time = time;
        this.phone = phone;
        this.doctor = doctor;
        this.injuryOrCondition = injuryOrCondition;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getGender(){
        return gender;
    }

    public void setGender(String gender){
        this.gender = gender;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getInjuryOrCondition() {
        return injuryOrCondition;
    }

    public void setInjuryOrCondition(String injuryOrCondition) {
        this.injuryOrCondition = injuryOrCondition;
    }

    public String getDoctor() {
        return doctor;
    }

    public void setDoctor(String doctor) {
        this.doctor = doctor;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }
}
