package com.example.projectmedilog;

public class aPatientsTable {

    String Name,Email,Phone,Gender,Age,BloodGroup;

    public aPatientsTable(String name, String email, String phone, String gender, String age, String bloodGroup) {
        Name = name;
        Email = email;
        Phone = phone;
        Gender = gender;
        Age = age;
        BloodGroup = bloodGroup;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getPhone() {
        return Phone;
    }

    public void setPhone(String phone) {
        Phone = phone;
    }

    public String getGender() {
        return Gender;
    }

    public void setGender(String gender) {
        Gender = gender;
    }

    public String getAge() {
        return Age;
    }

    public void setAge(String age) {
        Age = age;
    }

    public String getBloodGroup() {
        return BloodGroup;
    }

    public void setBloodGroup(String bloodGroup) {
        BloodGroup = bloodGroup;
    }
}
