package com.example.projectmedilog;

import javafx.scene.control.SelectionModel;

public class AppointmentTable {
    private static SelectionModel<Object> selectionModel;

    Integer id;
    String name, UserName, date, time, phone, doctor, injuryOrCondition;

    public AppointmentTable(Integer id, String name, String Usernme, String date, String time, String phone, String doctor, String injuryOrCondition){
        this.id = id;
        this.name = name;
        this.UserName = Usernme;
        this.date = date;
        this.time = time;
        this.phone = phone;
        this.doctor = doctor;
        this.injuryOrCondition = injuryOrCondition;
    }



    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUserName() {
        return UserName;
    }
    public void setUserName(String userName) {
        this.UserName = userName;
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

}
