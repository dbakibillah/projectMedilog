package com.example.projectmedilog;

import java.sql.Blob;

public class user {
    static String FullName;
    static String UserName;
    static String gender;
    static String age;
    static String phone;
    static String email;
    static  String address;
    static  String bloodGroup;
    static Blob image;



    public user(String FUllName, String UserName, String gender, String age, String phone, String email, String address, String bloodGroup, Blob image) {
        this.FullName = FUllName;
        this.UserName = UserName;
        this.gender = gender;
        this.age = age;
        this.phone = phone;
        this.email = email;
        this.image = image;
        this.address = address;
        this.bloodGroup = bloodGroup;
    }


    public static String getFullName() {
        return FullName;
    }

    public static void setFullName(String fullName) {
        user.FullName = fullName;
    }

    public static String getUserName() {
        return UserName;
    }

    public static void setUserName(String userName) {
        user.UserName = userName;
    }

    public String getGender(){
        return gender;
    }

    public void setGender(String gender){
        this.gender = gender;
    }

    public static String getAge() {
        return age;
    }

    public static void setAge(String age) {
        user.age = age;
    }

    public static String getPhone() {
        return phone;
    }

    public static void setPhone(String phone) {
        user.phone = phone;
    }

    public static String getEmail() {
        return email;
    }

    public static void setEmail(String email) {
        user.email = email;
    }

    public static String getAddress() {
        return address;
    }

    public static void setAddress(String address) {
        user.address = address;
    }

    public static String getBloodGroup() {
        return bloodGroup;
    }

    public static void setBloodGroup(String bloodGroup) {
        user.bloodGroup = bloodGroup;
    }

    public static Blob getImage() {
        return image;
    }

    public static void setImage(Blob image) {
        user.image = image;
    }

}
