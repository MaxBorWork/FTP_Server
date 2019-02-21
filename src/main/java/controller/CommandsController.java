package controller;

import model.*;
import org.apache.log4j.Logger;
import util.JDBCConnection;

import java.io.PrintWriter;

public class CommandsController {

    private JDBCConnection connection;
    private DataSocket dataSocket;
    private String currentDir = Config.ROOT;
    private String currentType;
    private String username;
    private Logger log = Logger.getLogger(CommandsController.class);

    public CommandsController() {
        connection = new JDBCConnection();
        dataSocket = new DataSocket();
    }

    public boolean getCommand(String line, PrintWriter writer, ReplyCode code) {
        MapOfCommand map = new MapOfCommand();

        System.out.println(line);
        log.info("Get command: " + line);
        String[] lineSlpit = line.split(" ");

        if (lineSlpit.length >= 1) {
            String command = lineSlpit[0];
            CommandProcess cP = (CommandProcess) map.commands.get(command);
            if(cP == null){
                writer.println(code.getCODE_500());
            } else {
                writer.println(cP.process(line, writer, code, this));
            }
            return false;
        } else {
            writer.println(code.getCODE_500());
            return true;
        }
    }

    public JDBCConnection getConnection() {
        return connection;
    }

    public void setConnection(JDBCConnection connection) {
        this.connection = connection;
    }

    public DataSocket getDataSocket() {
        return dataSocket;
    }

    public void setDataSocket(DataSocket dataSocket) {
        this.dataSocket = dataSocket;
    }

    public String getCurrentDir() {
        return currentDir;
    }

    public void setCurrentDir(String currentDir) {
        this.currentDir = currentDir;
    }

    public String getCurrentType() {
        return currentType;
    }

    public void setCurrentType(String currentType) {
        this.currentType = currentType;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
