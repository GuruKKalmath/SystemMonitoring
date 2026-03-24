package com.gurukkalmath.monitor.util;



import java.sql.DriverManager;
import java.sql.Connection;

public class DatabaseUtil {


    private static final String URL="jdbc:postgresql://localhost:5432/Systemmonitor";
    private static final String USER="postgres";
    private static final String PASSWORD="guru123";
    public static Connection getConnection() throws Exception{
        return  DriverManager.getConnection(URL,USER,PASSWORD);
    }


}
