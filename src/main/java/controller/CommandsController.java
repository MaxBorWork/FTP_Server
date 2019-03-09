package controller;

import model.*;
import org.apache.log4j.Logger;
import util.DataSocket;
import util.JDBCConnection;
import util.ServerSocketAccept;

import java.io.PrintWriter;

public class CommandsController {

    private JDBCConnection connection;
    private DataSocket dataSocket;
    private String currentDir = Config.ROOT;
    private String currentType;
    private String username;
    private PrintWriter writer;
    public static Reply reply;
    private Logger log = Logger.getLogger(CommandsController.class);

    public CommandsController() {
        ServerSocketAccept.loggerConfig(log);
        connection = new JDBCConnection();
        dataSocket = new DataSocket();
        reply = new Reply();
    }

    public boolean getCommand(String line, PrintWriter writer, ReplyCode code) {
        this.writer = writer;
        MapOfCommand map = new MapOfCommand();

        System.out.println(line);
        log.info("Got command: " + line);
        String[] lineSlpit = line.split(" ");

        if (!line.equals("")) {
            String command = lineSlpit[0];
            CommandProcess cP = (CommandProcess) map.commands.get(command);
            if(cP == null){
                writer.println("\t" + reply.codeToMessage.get(500).toString());
                System.out.println(reply.codeToMessage.get(500).toString());
                log.info(reply.codeToMessage.get(500).toString());
            } else {
                String response = cP.process(line, code, this);
                System.out.println("\t" + response);
                log.info(response);
                writer.println(response);
            }
            return false;
        } else {
            System.out.println("\t" + reply.codeToMessage.get(500).toString());
            writer.println(reply.codeToMessage.get(500).toString());
            log.info(reply.codeToMessage.get(500).toString());
            return true;
        }
    }

    public JDBCConnection getConnection() {
        return connection;
    }


    public DataSocket getDataSocket() {
        return dataSocket;
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

    public PrintWriter getWriter() {
        return writer;
    }

    public void setWriter(PrintWriter writer) {
        this.writer = writer;
    }
}
