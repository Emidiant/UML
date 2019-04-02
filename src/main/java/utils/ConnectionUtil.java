package utils;

import java.sql.*;

public class ConnectionUtil {
    private static final String url = "jdbc:h2:tcp://localhost/C:/Users/Alex/Desktop/studies/4semestr/UML/239store/UML/test;AUTO_SERVER=TRUE";
            //"jdbc:h2:file:C:/Users/Alex/Desktop/studies/4semestr/UML/239store/UML/test";//"jdbc:h2:~/test"; //вот это говно руинит
    private static final String login = "sa";
    private static final String password = "";

    static {
        try {
            Class.forName("org.h2.Driver");
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        }
    }

    public static Connection getConnection() {
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(url, login, password);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return connection;
    }
}

