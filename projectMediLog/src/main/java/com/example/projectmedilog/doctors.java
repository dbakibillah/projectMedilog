package com.example.projectmedilog;

import java.sql.Blob;

public class doctors {
     static String FullName, UserName, Password, Gender, Age, Phone, Degree, Department;
    static Blob image;

    public doctors(String fullName, String userName, String password, String gender, String age, String phone, String degree, String department) {
        this.FullName = fullName;
        this.UserName = userName;
        this.Password = password;
        this.Gender = gender;
        this.Age = age;
        this.Phone = phone;
        this.Degree = degree;
        this.Department = department;
    }

    public static String getFullName() {
        return FullName;
    }

    public static void setFullName(String fullName) {
        FullName = fullName;
    }

    public static String getUserName() {
        return UserName;
    }

    public static void setUserName(String userName) {
        UserName = userName;
    }

    public static String getPassword() {
        return Password;
    }

    public static void setPassword(String password) {
        doctors.Password = password;
    }

    public static String getGender() {
        return Gender;
    }

    public static void setGender(String gender) {
        doctors.Gender = gender;
    }

    public static String getAge() {
        return Age;
    }

    public static void setAge(String age) {
        doctors.Age = age;
    }

    public static String getPhone() {
        return Phone;
    }

    public static void setPhone(String phone) {
        doctors.Phone = phone;
    }

    public static String getDegree() {
        return Degree;
    }

    public static void setDegree(String degree) {
        doctors.Degree = degree;
    }

    public static String getDepartment() {
        return Department;
    }

    public static void setDepartment(String department) {
        doctors.Department = department;
    }
    public static Blob getImage() {
        return image;
    }

    public static void setImage(Blob image) {
        doctors.image = image;
    }
}
