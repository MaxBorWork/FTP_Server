package util;

import model.User;

import java.sql.*;
import java.util.List;

public class JDBCConnection {

    private static final String url = "jdbc:sqlite:main.db";
    private final String SQL_CREATE_TABLE = "CREATE TABLE IF NOT EXISTS User (\n"
            + "	id integer PRIMARY KEY,\n"
            + "	username varchar(255) NOT NULL,\n"
            + "	password varchar(255) NOT NULL\n"
            + ");";

    public JDBCConnection() {
        try {
            Connection con = DriverManager.getConnection(url);

            Statement statement = con.createStatement();

            statement.execute(SQL_CREATE_TABLE);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public boolean getUsername(String username) {

        try {
            Connection con = DriverManager.getConnection(url);

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
            Connection con = DriverManager.getConnection(url);

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
