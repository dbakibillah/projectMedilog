package com.example.projectmedilog;

public class user {
    static String firstName;
    static String lastName;
    static String gender;
    static String age;
    static String phone;
    static String email;

    static  String address;

    static  String bloodGroup;
    static String image;

    public user(String firstName, String lastName, String gender, String age, String phone, String email, String address, String bloodGroup, String image) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.gender = gender;
        this.age = age;
        this.phone = phone;
        this.email = email;
        this.image = image;
        this.address = address;
        this.bloodGroup = bloodGroup;
    }

    public static String getFirstName() {
        return firstName;
    }

    public static void setFirstName(String firstName) {
        user.firstName = firstName;
    }

    public static String getLastName() {
        return lastName;
    }

    public static void setLastName(String lastName) {
        user.lastName = lastName;
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

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        user.image = image;
    }

}
