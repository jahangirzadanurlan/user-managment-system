package com.example.usermanagmentsystem.config;

import lombok.SneakyThrows;

import java.sql.Connection;
import java.sql.DriverManager;

public abstract class DatabaseConfig {
    @SneakyThrows
    public Connection connect(){
        Class.forName("org.postgresql.Driver");
        String url="jdbc:postgresql://localhost:5432/user-managment-system";
        String username="postgres";
        String password="root";
        return DriverManager.getConnection(url,username,password);
    }
}
