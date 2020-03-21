package com.aws.db.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class BaseJDBC {

    static final String SELECT_NOW = "SELECT NOW()";
    private static final String URL = "jdbc:mysql://*****.eu-west-***.rds.amazonaws.com:3306/myDbName";
    private static final String USER_NAME = "myDbName";
    private static final String PASSWORD = "myDbPassword";

    public String getCurrentTime() {

        String currentTime = "time is unavailable";

        try {
            String url = URL;
            String username = USER_NAME;
            String password = PASSWORD;

            Connection conn = DriverManager.getConnection(url, username, password);
            Statement stmt = conn.createStatement();
            ResultSet resultSet = stmt.executeQuery(SELECT_NOW);

            if (resultSet.next()) {
                currentTime = resultSet.getObject(1).toString();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return currentTime;
    }
}
