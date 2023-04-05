package com.example.projectmedilog;

public class pTable {
    String name, createdby, date, disease;

    public pTable(String name, String createdby, String date, String disease) {
        this.name = name;
        this.createdby = createdby;
        this.date = date;
        this.disease = disease;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
}
