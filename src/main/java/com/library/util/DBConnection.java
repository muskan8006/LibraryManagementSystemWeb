package com.library.util;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBConnection {

    private static Connection conn;

    public static Connection getConnection() {
        try {
            if (conn == null) {
                Class.forName("org.postgresql.Driver");

                conn = DriverManager.getConnection(
                        "jdbc:postgresql://localhost:5432/library_db",
                        "postgres",
                        "muskan@1020");

                System.out.println("PostgreSQL Connected Successfully!");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return conn;
    }
}