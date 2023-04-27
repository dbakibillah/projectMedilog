package com.example.projectmedilog;

import java.sql.Blob;

public class DoctorTable {
    static String FullName, UserName, password, gender, age, phone, degree, department;
    static Blob image;


    public DoctorTable(String fullName, String UserName, String gender, String age, String phone, String degree, String department, Blob image) {
        this.FullName = fullName;
        this.UserName = UserName;
        //this.password = password;
        this.gender = gender;
        this.age = age;
        this.phone = phone;
        this.degree = degree;
        this.department = department;
        this.image = image;

    }

    public static Blob getImage() {
        return image;
    }

    public static void setImage(Blob image) {
        DoctorTable.image = image;
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
        return password;
    }

    public static void setPassword(String password) {
        DoctorTable.password = password;
    }

    public static String getGender() {
        return gender;
    }

    public static void setGender(String gender) {
        DoctorTable.gender = gender;
    }

    public static String getAge() {
        return age;
    }

    public static void setAge(String age) {
        DoctorTable.age = age;
    }

    public static String getPhone() {
        return phone;
    }

    public static void setPhone(String phone) {
        DoctorTable.phone = phone;
    }

    public static String getDegree() {
        return degree;
    }

    public static void setDegree(String degree) {
        DoctorTable.degree = degree;
    }

    public static String getDepartment() {
        return department;
    }

    public static void setDepartment(String department) {
        DoctorTable.department = department;
    }

    //    public String getFullName() {
//        return FullName;
//    }
//    public void setFullName(String fullName) {
//        this.FullName = fullName;
//    }
//    public String getUserName() {
//        return UserName;
//    }
//    public void setUserName(String userName) {
//        this.UserName = userName;
//    }
////    public String getPassword() {
////        return password;
////    }
////    public void setPassword(String password) {
////        this.password = password;
////    }
//
//    public String getGender() {
//        return gender;
//    }
//    public void setGender(String gender) {
//        this.gender = gender;
//    }
//    public String getAge() {
//        return age;
//    }
//    public void setAge(String age) {
//        this.age = age;
//    }
//public String getPhone() {
//        return phone;
//    }
//    public void setPhone(String phone) {
//        this.phone = phone;
//    }
//    public String getDegree() {
//        return degree;
//    }
//    public void setDegree(String degree) {
//        this.degree = degree;
//    }
//    public String getDepartment() {
//        return department;
//    }
//    public void setDepartment(String department) {
//        this.department = department;
//    }


}
