package com.example.projectmedilog;
import java.sql.*;


public class pPreDb {
    public static Connection getconnectionz() throws SQLException {


        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/projectmedilog", "root", "");
        return connection;
    }
}