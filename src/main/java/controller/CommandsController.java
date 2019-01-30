package controller;

import util.JDBCConnection;

public class CommandsController {

    private JDBCConnection connection;
    private String username;

    public CommandsController() {
        connection = new JDBCConnection();
    }

    public String userCommand(String message) {
        String[] messageSplit = message.split(" ");
        if (messageSplit.length > 1) {
            username = messageSplit[1];
            boolean user = connection.getUsername(username);
            if (user) {
                return "200";
            }
        }
        return "Error";
    }

    public String passwordCommand(String message) {
        String[] messageSplit = message.split(" ");
        String password = null;
        if (messageSplit.length > 1) {
            password = messageSplit[1];
            boolean user = connection.getUser(username, password);
            if (user) {
                return "200";
            }
        }
        return "Error";
    }

}
