package com.example.projectmedilog;

import java.sql.Blob;

public class adoctors {
    String FullName, UserName,  gender, age, phone, degree, department;
    Blob image;

    public adoctors(String fullName, String userName, String gender, String age, String phone, String degree, String department, Blob image) {
        FullName = fullName;
        UserName = userName;

        this.gender = gender;
        this.age = age;
        this.phone = phone;
        this.degree = degree;
        this.department = department;
        this.image = image;
    }

    public Blob getImage() {
        return image;
    }

    public void setImage(Blob image) {
        this.image = image;
    }

    public String getFullName() {
        return FullName;
    }

    public void setFullName(String fullName) {
        FullName = fullName;
    }

    public String getUserName() {
        return UserName;
    }

    public void setUserName(String userName) {
        UserName = userName;
    }



    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getDegree() {
        return degree;
    }

    public void setDegree(String degree) {
        this.degree = degree;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }
}
