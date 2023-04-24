package com.example.projectmedilog;

public class pTable {
    String name, UserName, createdby, date, disease, test, medicine;


    public pTable(String name, String UserName, String createdby, String date, String disease, String test,String medicine) {
        this.name = name;
        this.UserName = UserName;
        this.createdby = createdby;
        this.date = date;
        this.disease = disease;
        this.test = test;
        this.medicine = medicine;
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

    public String getCreatedby() {
        return createdby;
    }

    public void setCreatedby(String createdby) {
        this.createdby = createdby;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getDisease() {
        return disease;
    }

    public void setDisease(String disease) {
        this.disease = disease;
    }

    public String getTest() {
        return test;
    }

    public void setTest(String test) {
        this.test = test;
    }

    public String getMedicine() {
        return medicine;
    }

    public void setMedicine(String medicine) {
        this.medicine = medicine;
    }
}
