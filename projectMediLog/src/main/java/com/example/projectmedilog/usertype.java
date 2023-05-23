package com.example.projectmedilog;

public class usertype {
    static String type;
    static String username;


    public usertype(String username, String type) {
        this.type = type;
        this.username = username;
    }

    public static String getType() {
        return type;
    }

    public static void setType(String type) {
        usertype.type = type;
    }

    public static String getUserName() {
        return username;
    }

    public static void setUsername(String username) {
        usertype.username = username;
    }
}
