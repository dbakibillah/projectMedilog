package com.example.projectmedilog;

public class DoctorTable {
    String FullName, UserName, password,gender, age, phone, degree, department;
    public DoctorTable(String fullName, String UserName, String gender, String age, String phone , String degree, String department){
        this.FullName = fullName;
        this.UserName = UserName;
        //this.password = password;
        this.gender = gender;
        this.age = age;
        this.phone = phone;
        this.degree = degree;
        this.department = department;

    }
    public String getFullName() {
        return FullName;
    }
    public void setFullName(String fullName) {
        this.FullName = fullName;
    }
    public String getUserName() {
        return UserName;
    }
    public void setUserName(String userName) {
        this.UserName = userName;
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

}
