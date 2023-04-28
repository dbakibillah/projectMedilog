package com.example.projectmedilog;

import java.sql.Blob;

public class admin {
    static Blob image;
    static String UserName;
    public admin(String UserName, Blob image) {

        this.UserName = UserName;
        this.image = image;
    }



    public static Blob getImage() {
        return image;
    }

    public static void setImage(Blob image) {
        admin.image = image;
    }

    public static String getUserName() {
        return UserName;
    }

    public static void setUserName(String userName) {
        admin.UserName = userName;
    }
}
