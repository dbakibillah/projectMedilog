package com.example.projectmedilog;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import static java.sql.DriverManager.getConnection;

public class database {
    Connection connection = null;
    java.sql.PreparedStatement pst;

    public static Connection dbconnect() throws ClassNotFoundException, SQLException {

        Class.forName("com.mysql.jdbc.Driver");
        Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/projectmedilog","root","");
        return conn;
    }
}
