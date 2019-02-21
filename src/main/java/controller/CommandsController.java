package controller;

import model.*;
import org.apache.log4j.Logger;
import util.JDBCConnection;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

public class CommandsController {
    public JDBCConnection connection;
    public String username;
    public DataSocket dataSocket;
    public static String ROOT = "/etc/ftRoot";
    public String SPACE = " ";
    public String COMMA = ",";
    public String dataType = "I";
    public int SIZE_OF_COMMAND_WITHOUT_ARGUMENT = 1;
    public int SIZE_OF_COMMAND_WITH_ONE_ARGUMENT = 2;
    public int SIZE_OF_COMMAND_WITH_TWO_ARGUMENT = 3;
    public int FIRST_ARGUMENT_INDEX = 1;



    public static Logger log = Logger.getLogger(CommandsController.class);

    public CommandsController() {
        connection = new JDBCConnection();
        dataSocket = new DataSocket();
    }
}
