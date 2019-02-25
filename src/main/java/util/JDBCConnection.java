package util;

import model.User;

import java.sql.*;
import java.util.List;

public class JDBCConnection {

    private static final String url = "jdbc:mysql://localhost:3306/aipos";
    private static final String user = "root";
    private static final String pass = "кщще";

    public boolean getUsername(String username) {

        try {
            Connection con = DriverManager.getConnection(url, user, pass);

            Statement statement = con.createStatement();

            String userQuery = "SELECT username, password FROM User WHERE username ='" + username + "'";
            ResultSet resultSet = statement.executeQuery(userQuery);

            return resultSet.next();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    public boolean getUser(String username, String password) {

        try {
            Connection con = DriverManager.getConnection(url, user, pass);

            Statement statement = con.createStatement();

            String userQuery = "SELECT username, password FROM User WHERE username ='" + username + "' AND password='" +
                    password + "'";
            ResultSet resultSet = statement.executeQuery(userQuery);

            return resultSet.next();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }
}
