package ru.ifmo.uml.utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.*;
import java.util.Properties;

public class ConnectionUtil {
    /*private static final String url = "jdbc:h2:tcp://localhost/~/IdeaProjects/UML/test";
    private static final String login = "sa";
    private static final String password = "";*/

    static {
        try {
            Class.forName("org.h2.Driver");
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        }
    }

    public static Connection getConnection() {
        FileInputStream fis;
        Properties property = new Properties();
        Connection connection = null;
        try {
            fis = new FileInputStream("./src/main/resources/configs/db.properties");
            property.load(fis);
            String link = property.getProperty("db.link");
            String login = property.getProperty("db.login");
            String password = property.getProperty("db.password");
            connection = DriverManager.getConnection(link, login, password);
        } catch (IOException e) {
            System.err.println("No config file");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }
}

