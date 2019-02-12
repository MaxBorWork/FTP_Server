package controller;

import org.apache.log4j.Logger;
import util.JDBCConnection;
import model.Messages;
import model.ReplyCode;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.List;

public class CommandsController {

    private JDBCConnection connection;
    private String username;
    private String dataType;
    public static String ROOT = "/etc/ftRoot";
    private String SPACE = " ";
    private int SIZE_OF_COMMAND_WITHOUT_ARGUMENT = 1;
    private int SIZE_OF_COMMAND_WITH_ONE_ARGUMENT = 2;
    private int SIZE_OF_COMMAND_WITH_TWO_ARGUMENT = 3;
    private int FIRST_ARGUMENT_INDEX = 1;

    private static Logger log = Logger.getLogger(CommandsController.class);

    public CommandsController() {
        connection = new JDBCConnection();
    }

    public String userCommand(String message) {
        String[] messageSplit = message.split(SPACE);

        if (messageSplit.length == SIZE_OF_COMMAND_WITH_ONE_ARGUMENT) {

            username = messageSplit[FIRST_ARGUMENT_INDEX];
            boolean user = connection.getUsername(username);
            if (user) {
                log.info("username is right");
                return ReplyCode.CODE_331;
            } else {
                log.info("There is no such username in DB");
                return ReplyCode.CODE_530;
            }
        } else {
            log.info("Wrong command");
            return ReplyCode.CODE_501;
        }

    }

    public String passwordCommand(String message) {
        String[] messageSplit = message.split(SPACE);

        if (messageSplit.length ==  SIZE_OF_COMMAND_WITH_ONE_ARGUMENT) {

            String password  = messageSplit[FIRST_ARGUMENT_INDEX];
            boolean user = connection.getUser(username, password);

            if (user) {
                log.info("password is right");
                return ReplyCode.CODE_230;
            } else {
                log.info("Wrong pasword");
                return ReplyCode.CODE_530;
            }

        } else {
            log.info("Wrong command");
            return ReplyCode.CODE_501;
        }

    }


    public String systCommand(String message) {
        String[] messageSplit = message.split(SPACE);

        if (messageSplit.length ==  SIZE_OF_COMMAND_WITHOUT_ARGUMENT) {
            return ReplyCode.CODE_215;
        }

        else return ReplyCode.CODE_501;


    }

    public String removeDirectoryCommand(String message) {

        String[] messageSplit = message.split(SPACE);

        if (messageSplit.length == SIZE_OF_COMMAND_WITH_ONE_ARGUMENT) {
            File file = new File(ROOT + File.pathSeparator + messageSplit[FIRST_ARGUMENT_INDEX]);

            if (file.isDirectory() && file.delete()) {
                return ReplyCode.CODE_200;
            } else {
                return "Error"; // либо не удалена либо не директория
            }

        } else {
            return ReplyCode.CODE_501;
        }
    }

    public String deleteCommand(String message) {

        String[] messageSplit = message.split(SPACE);

        if (messageSplit.length == SIZE_OF_COMMAND_WITH_ONE_ARGUMENT) {
            File file = new File(ROOT + File.pathSeparator + messageSplit[FIRST_ARGUMENT_INDEX]);

            if (!file.isDirectory() && file.delete()) {
                return ReplyCode.CODE_200;
            } else {
                return "Error"; // либо не удалена либо не файл
            }
        } else {
            return ReplyCode.CODE_501;
        }

    }

    public String makeDirectoryCommand(String message) {

        String[] messageSplit = message.split(SPACE);

        if (messageSplit.length == SIZE_OF_COMMAND_WITH_ONE_ARGUMENT) {
            File file = new File(ROOT + File.pathSeparator + messageSplit[FIRST_ARGUMENT_INDEX]);

            if (file.mkdir()) {
                return ReplyCode.CODE_200;
            } else {
                return "Error"; //не создали
            }
        } else {
            return ReplyCode.CODE_501;
        }
    }

    public String listCommand(String message, Messages answer) throws IOException {
        String[] messageSplit = message.split(SPACE);

        if (messageSplit.length == SIZE_OF_COMMAND_WITHOUT_ARGUMENT) {
            answer.printDirectoryList(ROOT);
        } else if (messageSplit.length == SIZE_OF_COMMAND_WITH_ONE_ARGUMENT) {
            String path = ROOT + "/" + messageSplit[1];
            answer.printDirectoryList(path);
        } else {
            return ReplyCode.CODE_501;
        }
        return ReplyCode.CODE_226;
    }

    public String changeWorkDirCommand(String message){
        String[] messageSplit = message.split(SPACE);

        if (messageSplit.length == SIZE_OF_COMMAND_WITH_ONE_ARGUMENT) {
            File rootNew = new File(messageSplit[FIRST_ARGUMENT_INDEX]);

            if(rootNew.isDirectory() && rootNew.exists()){
                ROOT = messageSplit[FIRST_ARGUMENT_INDEX];
                return ReplyCode.CODE_200;

            } else return ReplyCode.CODE_200;// либо нет директории либо не лиректория

        } else return ReplyCode.CODE_501;
    }

    public String printWorkDirCommand(){
        log.info("printing root directory, directory is " + ROOT);
        return ReplyCode.CODE_257;
    }



    public String retrieveCommand(String message) {
        String[] messageSplit = message.split(" ");

        return "Error";
    }


    public String storeCommand(String message) {
        String[] messageSplit = message.split(" ");

        return "Error";
    }


    public String portCommand(String message) {
        log.info("PORT command recieved");
        String[] messageSplit = message.split(" ");

        if (messageSplit.length == SIZE_OF_COMMAND_WITH_ONE_ARGUMENT) {
            String[] hostAndPorts = messageSplit[1].split(",");
            String host = hostAndPorts[0] +"."+hostAndPorts[1] +"."+ hostAndPorts[2] + "."+hostAndPorts[3];
           // String ports[] = new String[2];
          //  ports[0] = hostAndPorts[4];
          //  ports[1] = hostAndPorts[5];
            return ReplyCode.CODE_200;
            //проверка на то свобоодны ли они77?
        } else return ReplyCode.CODE_501;
    }

    public String getType(String message) {
        String[] messageSplit = message.split(" ");

        if (messageSplit.length == SIZE_OF_COMMAND_WITH_ONE_ARGUMENT) {
            dataType = messageSplit[1];
            if (dataType.equals("A")) {
                log.info("type of transferring data is ASCII");
            } else if (dataType.equals("I")) {
                log.info("type of transferring data is binary");
            }
            else {
                return ReplyCode.CODE_501;
            }
            return ReplyCode.CODE_200;
        }
        else {
            return ReplyCode.CODE_501;
        }
    }

    public String pasvResponse() {
        log.info("sent data connection address 127.0.0.1:20");
        String resp = ReplyCode.CODE_227 + "(127,0,0,1," + 20 /256 + "," + 20 % 256 + ")";
        return resp;
    }

}
