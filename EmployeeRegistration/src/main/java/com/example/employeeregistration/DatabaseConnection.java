package com.example.employeeregistration;
import java.sql.*;
import java.sql.DriverManager;


public class DatabaseConnection {
    public Connection databaseLink;
    public Connection getConnection(){
        String user="root";
        String password="MJR@9120s";
        String url="jdbc:mysql://localhost:3306/employeeDetails";

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            databaseLink=DriverManager.getConnection(url,user,password);
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return databaseLink;
    }
    public Connection getAdminConnection(){
        String username = "root";
        String password = "MJR@9120s";
        String url = "jdbc:mysql://localhost:3306/admin";

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            databaseLink=DriverManager.getConnection(url,username,password);
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return databaseLink;
    }
}
