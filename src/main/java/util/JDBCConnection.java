package util;

import model.Config;
import org.apache.log4j.Logger;

import java.sql.*;

public class JDBCConnection {

    private Logger log = Logger.getLogger(JDBCConnection.class);

    public JDBCConnection() {
        try {
            Connection con = DriverManager.getConnection(Config.SQLITE_URL);

            Statement statement = con.createStatement();

            statement.execute(Config.SQL_CREATE_TABLE);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public boolean getUsername(String username) {

        try {
            Connection con = DriverManager.getConnection(Config.SQLITE_URL);

            Statement statement = con.createStatement();

            String userQuery = "SELECT username, password FROM User WHERE username ='" + username + "'";
            ResultSet resultSet = statement.executeQuery(userQuery);
            log.debug("querying for username " + username);

            return resultSet.next();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    public boolean getUser(String username, String password) {

        try {
            Connection con = DriverManager.getConnection(Config.SQLITE_URL);

            Statement statement = con.createStatement();

            String userQuery = "SELECT username, password FROM User WHERE username ='" + username + "' AND password='" +
                    password + "'";
            ResultSet resultSet = statement.executeQuery(userQuery);
            log.debug("querying password for user with username " + username);

            return resultSet.next();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

}
