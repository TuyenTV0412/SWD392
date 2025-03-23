package DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
/**
 *
 * @author FPT University - PRJ301
 */
public class DBContext {

    protected Connection connection;

    public Connection getConnection() {
        try {
        String user = "sa";
        String pass = "12345678";
        String url = "jdbc:sqlserver://DESKTOP-FJJ4KE9\\SQLEXPRESS:1433;databaseName=TourBookingOnline";
        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        return DriverManager.getConnection(url, user, pass);
    } catch (ClassNotFoundException | SQLException ex) {
        System.out.println("Connection error: " + ex.getMessage());
        return null;
    }
}

    public static void main(String[] args) {
        System.out.println(new DBContext().connection);
    }

}
