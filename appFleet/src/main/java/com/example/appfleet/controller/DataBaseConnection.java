package com.example.appfleet.controller;

import java.sql.Connection;
import java.sql.DriverManager;

public class DataBaseConnection {

    private static String url = "jdbc:mysql://localhost:3306/appdb";
    private static String user = "root";
    private static String password = "1234";

    public static Connection getConnection(){
        Connection connection;
        try {
            System.out.println("11");
            connection = DriverManager.getConnection(url, user, password);
            return connection;
        }catch (Exception e){
            System.out.println("Error: " + e.getMessage());
            return null;
        }
    }
}
