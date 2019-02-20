package controller;

import model.*;
import org.apache.log4j.Logger;
import util.JDBCConnection;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

public class CommandsController {

    private JDBCConnection connection;
    private String username;
    private DataSocket dataSocket;
    public static String ROOT = "/etc/ftRoot";
    private String SPACE = " ";
    private String COMMA = ",";
    private String dataType = "I";
    private int SIZE_OF_COMMAND_WITHOUT_ARGUMENT = 1;
    private int SIZE_OF_COMMAND_WITH_ONE_ARGUMENT = 2;
    private int SIZE_OF_COMMAND_WITH_TWO_ARGUMENT = 3;
    private int FIRST_ARGUMENT_INDEX = 1;

    private static Logger log = Logger.getLogger(CommandsController.class);

    public CommandsController() {
        connection = new JDBCConnection();
        dataSocket = new DataSocket();
    }

    public String userCommand(String message) {
        String[] messageSplit = message.split(SPACE);

        if (messageSplit.length == SIZE_OF_COMMAND_WITH_ONE_ARGUMENT) {

            username = messageSplit[FIRST_ARGUMENT_INDEX];
            boolean user = connection.getUsername(username);
            if (user) {
                log.info(LogMessages.USERNAME_OK_MESSAGE);
                return ReplyCode.CODE_331;
            } else {
                log.info(LogMessages.NO_SUCH_USER_MESSAGE);
                return ReplyCode.CODE_530;
            }
        } else {
            log.info(LogMessages.WRONG_COMMAND_MESSAGE);
            return ReplyCode.CODE_500;
        }

    }

    public String passwordCommand(String message) {
        String[] messageSplit = message.split(SPACE);

        if (messageSplit.length ==  SIZE_OF_COMMAND_WITH_ONE_ARGUMENT) {

            String password  = messageSplit[FIRST_ARGUMENT_INDEX];
            boolean user = connection.getUser(username, password);

            if (user) {
                log.info(LogMessages.PASSWORD_OK_MESSAGE);
                return ReplyCode.CODE_230;
            } else {
                log.info(LogMessages.WRONG_PASSWORD_MESSAGE);
                return ReplyCode.CODE_530;
            }

        } else {
            log.info(LogMessages.WRONG_COMMAND_MESSAGE);
            return ReplyCode.CODE_500;
        }
    }

    public String systCommand(String message) {
        String[] messageSplit = message.split(SPACE);

        if (messageSplit.length ==  SIZE_OF_COMMAND_WITHOUT_ARGUMENT) {
            log.info(LogMessages.SYSTEM_TYPE_MESSAGE );
            return ReplyCode.CODE_215;
        } else {
            log.info(LogMessages.WRONG_COMMAND_MESSAGE);
            return ReplyCode.CODE_500;
        }
    }

    public String printWorkDirCommand(String message){
        String[] messageSplit = message.split(SPACE);

        if (messageSplit.length == SIZE_OF_COMMAND_WITHOUT_ARGUMENT) {
            log.info(LogMessages.PRINT_ROOT_DIRECTORY_MESSAGE + ROOT);
            return ReplyCode.CODE_257;
        } else {
            log.info(LogMessages.WRONG_COMMAND_MESSAGE);
            return ReplyCode.CODE_500;
        }
    }

    public String pasvResponse(String message) {
        String[] messageSplit = message.split(SPACE);
        if (messageSplit.length == SIZE_OF_COMMAND_WITHOUT_ARGUMENT) {
            log.info(LogMessages.SENT_ADDRESS_MESSAGE);
//            dataSocket.startThread(Config.PORT_20_INT);
            return ReplyCode.CODE_227;
        } else {
            log.info(LogMessages.WRONG_COMMAND_MESSAGE);
            return ReplyCode.CODE_500;
        }

    }

    public String getType(String message) {
        String[] messageSplit = message.split(SPACE);

        if (messageSplit.length == SIZE_OF_COMMAND_WITH_ONE_ARGUMENT) {
            dataType = messageSplit[1];

            if (dataType.equals(Config.TYPE_A)) {
                log.info(LogMessages.TYPE_OF_DATA_IS_A_MESSAGE);
                return ReplyCode.CODE_200;
            } else if (dataType.equals(Config.TYPE_I)) {
                log.info(LogMessages.TYPE_OF_DATA_IS_I_MESSAGE);
                return ReplyCode.CODE_200;
            } else {
                log.info(LogMessages.WRONG_COMMAND_MESSAGE);
                return ReplyCode.CODE_501;
            }
        }
        else {
            log.info(LogMessages.WRONG_COMMAND_MESSAGE);
            return ReplyCode.CODE_501;
        }

    }

    public String portCommand(String message) {
        String[] messageSplit = message.split(SPACE);

        if (messageSplit.length == SIZE_OF_COMMAND_WITH_ONE_ARGUMENT) {
            String[] hostAndPorts = messageSplit[1].split(COMMA);
            String host = hostAndPorts[0] +"."+hostAndPorts[1] +"."+ hostAndPorts[2] + "."+hostAndPorts[3];
            int port = Integer.parseInt(hostAndPorts[4])* Config.BIT_SHIFT + Integer.parseInt(hostAndPorts[5]);
            if (port != Config.PORT_20_INT) {
                dataSocket.startThread(port);
            }
            log.info(LogMessages.PORT_COMMAND_MESSAGE);
            return ReplyCode.CODE_200;
        } else return ReplyCode.CODE_501;
    }

    public String removeDirectoryCommand(String message) {
        String[] messageSplit = message.split(SPACE);

        if (messageSplit.length == SIZE_OF_COMMAND_WITH_ONE_ARGUMENT) {
            File dir = new File(ROOT + File.pathSeparator + messageSplit[FIRST_ARGUMENT_INDEX]);

            if (dir.isDirectory() && dir.delete()) {
                return ReplyCode.CODE_250;
            } else if (dir.isDirectory() && !dir.delete()) {
                removeNotNullDir(dir);
                if (dir.delete()) {
                    return ReplyCode.CODE_250;
                } else {
                    return ReplyCode.CODE_550;
                }
            } else {
                return ReplyCode.CODE_550;
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
                return ReplyCode.CODE_250;
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

    public String listCommand(String message, PrintWriter writer) throws IOException {
        String[] messageSplit = message.split(SPACE);
        if (messageSplit.length == SIZE_OF_COMMAND_WITHOUT_ARGUMENT) {
            writer.println("150 ASCII data connection for /etc/root (127.0.0.1,20) (0 bytes)");
            dataSocket.createDataConnection(ROOT, Commands.LIST);
        } else if (messageSplit.length == SIZE_OF_COMMAND_WITH_ONE_ARGUMENT) {
            writer.println("150 ASCII data connection for /etc/root (127.0.0.1,20) (0 bytes)");
            String path = ROOT + "/" + messageSplit[1];
            dataSocket.createDataConnection(path, Commands.LIST);
        } else {
            return ReplyCode.CODE_501;
        }
        return ReplyCode.CODE_226;
    }

    public String changeWorkDirCommand(String message, PrintWriter writer){
        String[] messageSplit = message.split(SPACE);

        if (messageSplit.length == SIZE_OF_COMMAND_WITH_ONE_ARGUMENT) {
            String fullDirPath = messageSplit[FIRST_ARGUMENT_INDEX];
            if (!fullDirPath.contains(ROOT)) {
                fullDirPath = ROOT + "/" + messageSplit[FIRST_ARGUMENT_INDEX];

            }
            File rootNew = new File(fullDirPath);

            if(rootNew.isDirectory() && rootNew.exists()){
//                dataSocket.createDataConnection(rootNew.getAbsolutePath(), null);
                return ReplyCode.CODE_250;

            } else return ReplyCode.CODE_550;

        } else return ReplyCode.CODE_501;
    }

    public String printWorkDirCommand(){
        log.info("printing root directory, directory is " + ROOT);
        return ReplyCode.CODE_257;
    }

    public String retrieveCommand(String message, PrintWriter writer) {
        String[] messageSplit = message.split(" ");
        if (messageSplit.length == SIZE_OF_COMMAND_WITH_ONE_ARGUMENT) {
            String filename = messageSplit[FIRST_ARGUMENT_INDEX];
            if (!filename.contains("/")) {
                filename = ROOT + "/" + messageSplit[FIRST_ARGUMENT_INDEX];
            }
            writer.println("150 ASCII data connection for /etc/root (127.0.0.1,20) (0 bytes)");
            dataSocket.createDataConnection(filename, Commands.RETR);
        } else return ReplyCode.CODE_501;
        return ReplyCode.CODE_226;
    }

    public String storeCommand(String message, PrintWriter writer) {
        String[] messageSplit = message.split(" ");
        if (messageSplit.length == SIZE_OF_COMMAND_WITH_ONE_ARGUMENT) {
            String filename = messageSplit[FIRST_ARGUMENT_INDEX];
            if (!filename.contains("/")) {
                filename = ROOT + "/" + messageSplit[FIRST_ARGUMENT_INDEX];
            }
            writer.println("150 ASCII data connection for /etc/root (127.0.0.1,20) (0 bytes)");
            dataSocket.createDataConnection(filename, Commands.STOR);
        } else return ReplyCode.CODE_501;
        return ReplyCode.CODE_226;
    }

    private void removeNotNullDir(File dir) {
        String[] fileNames = dir.list();
        if (fileNames != null) {
            int removedFromDirFilesCount = 0;
            for (String fileName : fileNames) {
                File file = new File(dir.getPath(),fileName);
                if (file.delete()) {
                    log.info("file " + fileName + " was successfully deleted");
                    removedFromDirFilesCount++;
                } else {
                    log.info("can't remove file " + fileName);
                }
            }
            log.info("was successfully removed " + removedFromDirFilesCount + " files");
        }
    }

}
