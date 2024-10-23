package com.kkp.connection;

import java.sql.*;

public class ConnectDatabase {
    
    private Connection connection;
    
    public Connection connect() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            String url = "jdbc:mysql://localhost:3306/db_kkp";
            connection = DriverManager.getConnection(url, "root", "");
            System.out.println("Koneksi Berhasil.");
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println("Koneksi Gagal: " + e.getMessage());
        }
        return connection;
    }
}
