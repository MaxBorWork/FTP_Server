package controller;

import org.apache.log4j.Logger;
import util.JDBCConnection;
import model.Messages;
import model.ReplyCode;

import java.io.File;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

public class CommandsController {

    private JDBCConnection connection;
    private String username;
    private String ROOT = "/etc/ftRoot";
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
            System.out.println(user);
            if (user) {
                log.info("username is right");
                return ReplyCode.CODE_331;
            } else {
                log.info("There is no such username in DB");
                return ReplyCode.CODE_200; ///нет такого пользователя!!!!!!!!!!!!
            }

        } else return ReplyCode.CODE_500;

    }

    public String passwordCommand(String message) {
        String[] messageSplit = message.split(SPACE);

        if (messageSplit.length ==  SIZE_OF_COMMAND_WITH_ONE_ARGUMENT) {

            String password  = messageSplit[FIRST_ARGUMENT_INDEX];
            boolean user = connection.getUser(username, password);

            if (user) {
                return ReplyCode.CODE_200;
            } else {
                return ReplyCode.CODE_200; ///не верный пароль!!!!!!!!!!
            }

        } else return ReplyCode.CODE_500;

    }


    public String systCommand(String message) {
        String[] messageSplit = message.split(SPACE);

        if (messageSplit.length ==  SIZE_OF_COMMAND_WITHOUT_ARGUMENT) return ReplyCode.CODE_215;

        else return ReplyCode.CODE_500;


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
            return ReplyCode.CODE_500;
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
            return ReplyCode.CODE_500;
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
            return ReplyCode.CODE_500;
        }
    }

    public String listCommand(String message, Messages answer) {
        String[] messageSplit = message.split(SPACE);

        if (messageSplit.length == SIZE_OF_COMMAND_WITHOUT_ARGUMENT) {
            File root = new File(ROOT);

            List files = new ArrayList<String>();
            List dirs = new  ArrayList<String>();

            if(root.listFiles() != null){
                for(File file: root.listFiles()){

                    if(!file.isDirectory()) files.add(file.getName());
                    else  dirs.add(file.getName());

                }
            } else return ReplyCode.CODE_500;

            answer.printFileList(files);
            answer.printDirectoryList(dirs);

            return ReplyCode.CODE_200;

        } else return ReplyCode.CODE_500;
    }

    public String changeWorkDirCommand(String message){
        String[] messageSplit = message.split(SPACE);

        if (messageSplit.length == SIZE_OF_COMMAND_WITH_ONE_ARGUMENT) {
            File rootNew = new File(messageSplit[FIRST_ARGUMENT_INDEX]);

            if(rootNew.isDirectory() && rootNew.exists()){
                ROOT = messageSplit[FIRST_ARGUMENT_INDEX];
                return ReplyCode.CODE_200;

            } else return ReplyCode.CODE_200;// либо нет директории либо не лиректория

        } else return ReplyCode.CODE_500;
    }

    public String printWorkDirCommand(){
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
        String[] messageSplit = message.split(" ");

        if (messageSplit.length == 2) {
            String[] hostAndPorts = messageSplit[1].split(",");
            String host = hostAndPorts[0] +"."+hostAndPorts[1] +"."+ hostAndPorts[2] + "."+hostAndPorts[3];
           // String ports[] = new String[2];
          //  ports[0] = hostAndPorts[4];
          //  ports[1] = hostAndPorts[5];
            return ReplyCode.CODE_200;
            //проверка на то свобоодны ли они77?
        } else return ReplyCode.CODE_500;

    }





}
