package com.example.projectmedilog;

public class DoctorTable {
    String name, email, password,gender, age, phone, degree, department, date;
    public DoctorTable(String name, String email,String gender,String age,String phone , String degree, String department, String date){
        this.name = name;
        this.email = email;
        //this.password = password;
        this.gender = gender;
        this.age = age;
        this.phone = phone;
        this.degree = degree;
        this.department = department;
        this.date = date;

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
//    public String getPassword() {
//        return password;
//    }
//    public void setPassword(String password) {
//        this.password = password;
//    }

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
    public String getDate() {
        return date;
    }
    public void setDate(String date) {
        this.date = date;
    }

}
